package e_commerce.epda_assignment.model.input;

import lombok.Data;

@Data
public class LineItem_Input {
    private String customerID;
    private String productTypeID;
    private int quantity;
    private Double price;

}
