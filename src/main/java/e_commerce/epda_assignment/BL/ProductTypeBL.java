package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.ProductTypes_I;
import e_commerce.epda_assignment.dao.Rating_I;
import e_commerce.epda_assignment.model.ProductType;
import e_commerce.epda_assignment.model.input.Product_Input;
import e_commerce.epda_assignment.model.output.ProductOutput;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Product_Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTypeBL implements ProductTypeBLI {
    @Autowired
    ProductTypes_I productsI;
    @Autowired
    Rating_I ratingI;
    public boolean createProduct(Product_Input input){
        return productsI.create(input.toProduct());
    }
    public respondOutput updateProduct(Product_Update input){
        try {
            ProductType productType = productsI.getProductbyId(input.getProductId());
            if (productType == null) {
                return new respondOutput(false);
            }
            return new respondOutput(productsI.update(input.toProduct(productType)));
        }
        catch (Exception e){
            return new respondOutput(false);
        }
    }
    public List<ProductType> getAllProduct(){
        return productsI.getAllProduct();
    }

    public ProductOutput getProductOutputByProductId (String productId) {
        ProductType productType = productsI.getProductbyId(productId);
        return new ProductOutput(productType, ratingI.findByConditionEqual("productTypeID",productType.getId()));
    }

    public List<ProductOutput> getAllProductBySellerId (String sellerId){
        return getProductOutputByProduct(productsI.findByConditionEqual("sellerID", sellerId));
    }

    public List<ProductOutput> getProductOutputByProduct (List<ProductType> productType){
        return productType
                .stream()
                .map(x->getProductOutputByProduct(x))
                .collect(Collectors.toList());
    }
    public ProductOutput getProductOutputByProduct (ProductType productType){
        return new ProductOutput(productType, ratingI.findByConditionEqual("productTypeID",productType.getId()));
    }

    public List<ProductType> searchProduct(String name){
        return productsI.findByConditionLike("productName", name);
    }

    public List<ProductType> searchProductBySeller(String sellerId, String name){
        return productsI.searchProductBySeller(sellerId, name);
    }

    public boolean deleteProduct(String productId) {
        return productsI.delete(productsI.getProductbyId(productId));
    }
}
