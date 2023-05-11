package e_commerce.epda_assignment.model;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userID;
    private String adminFirstName;
    private String adminLastName;
    private String adminPhoneNO;
    private Static.accType accountType = Static.accType.admin;
    private String imageURL;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
}
