package e_commerce.epda_assignment.model;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userID;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerPhoneNO;
    private String imageURL;
    private Static.accType accountType = Static.accType.seller;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private Double wallet = 0.0;
    private boolean isApproved = false;
}
