package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String orderID;
    private String sellerID;
    private String card;
    private String expireDate;
    private String cvc;
    private Double price;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
}
