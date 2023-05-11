package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "assignment")
public class Line_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String orderID;
    private String productTypeId;
    private int quantity;
    private Double unitPrice;
    private Double price;
}
