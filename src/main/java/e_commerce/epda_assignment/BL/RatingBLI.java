package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.Rating;
import e_commerce.epda_assignment.model.input.Rating_Input;
import e_commerce.epda_assignment.model.output.respondOutput;

import java.util.List;

public interface RatingBLI {
    respondOutput createRating (Rating_Input input);
    List<Rating> getRatingByProductTypeId (String productTypeId);
    List<Rating> getRatingByOrderId (String orderId);
}
