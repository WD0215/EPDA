package e_commerce.epda_assignment.model;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String customerID;
    private Static.orderStatus orderStatus;
    private LocalDateTime estimateDeliveryTimestamp = LocalDateTime.now().plusDays(7);
    private Double deliveryCost;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private String addressID;
}
