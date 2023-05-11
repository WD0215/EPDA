package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.ProductType;
import e_commerce.epda_assignment.model.Rating;
import lombok.Data;

import java.util.List;

@Data
public class ProductOutput {
    private ProductType productType;
    private List<Rating> ratings;
    public ProductOutput (ProductType productType, List<Rating> ratings){
        this.productType = productType;
        this.ratings = ratings;
    }
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
