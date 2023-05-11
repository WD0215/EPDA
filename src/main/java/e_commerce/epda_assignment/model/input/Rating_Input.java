package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.Rating;
import lombok.Data;

@Data
public class Rating_Input {
    private String productTypeID;
    private String orderID;
    private String userId;
    private int ratingValue;
    private String description;
    //If Seller's reply to review
    //Assign customer's rating id to this field
    private String subReviewOfId;
    public Rating toRating(){
        Rating rating = new Rating();
        rating.setProductTypeID(productTypeID);
        rating.setOrderID(orderID);
        rating.setUserId(userId);
        rating.setRatingValue(ratingValue);
        rating.setDescription(description);
        rating.setSubReviewOfId(subReviewOfId);
        return rating;
    }

}