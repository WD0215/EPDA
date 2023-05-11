package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.utility.Static;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserLogin_Input {
    private String email;
    private String password;
}
