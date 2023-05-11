package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.model.input.UserLogin_Input;
import e_commerce.epda_assignment.model.output.LoginOutput;

public interface UserBLI {
    LoginOutput login (UserLogin_Input input);
}
