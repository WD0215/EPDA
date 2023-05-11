package e_commerce.epda_assignment.model.update;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Order_Update {
    private String id;
    private String paymentID;
    private String customerID;
    private String orderStatus;
    private String estimateDeliveryTimestamp;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
}
