package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.ProductType;
import lombok.Data;

@Data
public class Product_Update {
    private String productId;
    private String productName;
    private String productDescription;
    private Double price;
    private String imageURL;

    public ProductType toProduct(ProductType productType){
        productType.setProductName(productName);
        productType.setProductDescription(productDescription);
        productType.setPrice(price);
        if (imageURL != null) {
            productType.setImageURL(imageURL);
        }
        return productType;
    }
}
