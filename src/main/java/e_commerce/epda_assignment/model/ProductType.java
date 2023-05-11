package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "assignment")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String sellerID;
    private String productName;
    private String productDescription;
    private Double price;
    private String imageURL;
}
