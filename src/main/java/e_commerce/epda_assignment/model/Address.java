package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Data
@Entity
@Table(schema = "assignment",name = "Address")
public class    Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private boolean isPrimary;
    private String userID;
    private String unit;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
