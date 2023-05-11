package e_commerce.epda_assignment.model;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNO;
    private Static.accType accountType = Static.accType.client;
    private String imageURL;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private String customerIC;
}
