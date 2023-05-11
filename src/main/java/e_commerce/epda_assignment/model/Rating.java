package e_commerce.epda_assignment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "assignment")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String productTypeID;
    private String orderID;
    private String userId;
    private Integer ratingValue;
    private String description;
    private String subReviewOfId;
    private LocalDateTime creationTimestamp = LocalDateTime.now();
}
