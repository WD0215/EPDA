package e_commerce.epda_assignment.model;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(schema = "assignment")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(unique = true)
    private String email;
    public Static.accType accountType;
    private String password;
}
