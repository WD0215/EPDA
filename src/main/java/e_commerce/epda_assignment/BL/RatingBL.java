package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Rating_I;
import e_commerce.epda_assignment.model.Rating;
import e_commerce.epda_assignment.model.input.Rating_Input;
import e_commerce.epda_assignment.model.output.ProductOutput;
import e_commerce.epda_assignment.model.output.respondOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.OptionalDouble;

@Component
public class RatingBL implements RatingBLI {
    @Autowired
    Rating_I ratingI;
    @Autowired
    ProductTypeBLI productTypeBLI;


    public respondOutput createRating (Rating_Input input){
        return new respondOutput(ratingI.create(input.toRating()));
    }

    public List<Rating> getRatingByProductTypeId (String productTypeId) {
        return ratingI.findByConditionEqual("productTypeID", productTypeId);
    }
    public List<Rating> getRatingByOrderId (String orderId) {
        return ratingI.findByConditionEqual("orderID", orderId);
    }

    public String getAverageRatingByProductTypeId(String productTypeId) {
        List<Rating> ratings = getRatingByProductTypeId(productTypeId);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String formattedNumber = decimalFormat.format(ratings.stream().mapToInt(Rating::getRatingValue).average());
        return formattedNumber;
    }

    public String getAverageRatingBySellerId (String sellerId) {
        List<ProductOutput> productOutputs = productTypeBLI.getAllProductBySellerId(sellerId);
        OptionalDouble rating = productOutputs.stream().mapToDouble(x-> Double.parseDouble(getAverageRatingByProductTypeId(x.getProductType().getId()))).average();
        Double doubleValue = rating.isPresent() ? rating.getAsDouble() : null;
        if (doubleValue == null) {
            return null;
        }
        else {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            String formattedNumber = decimalFormat.format(doubleValue);
            return formattedNumber;
        }
    }

}
