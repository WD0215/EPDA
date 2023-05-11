package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.ProductType;
import lombok.Data;

@Data
public class Product_Input {
    private String productName;
    private String sellerID;
    private String productDescription;
    private Double price;
    private String imageURL;
    public ProductType toProduct(){
        ProductType productType = new ProductType();
        productType.setSellerID(sellerID);
        productType.setProductName(productName);
        productType.setProductDescription(productDescription);
        productType.setPrice(price);
        productType.setImageURL(imageURL);
        return productType;
    }
}
