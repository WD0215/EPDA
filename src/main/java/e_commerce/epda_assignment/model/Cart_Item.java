package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String customerID;
    private String productTypeID;
    private int quantity;
    private Double price;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private boolean status;

}
