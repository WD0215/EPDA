package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Address_I;
import e_commerce.epda_assignment.dao.Users_I;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.model.input.UserLogin_Input;
import e_commerce.epda_assignment.model.output.LoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBL implements UserBLI{
    @Autowired
    private Address_I addressI;
    @Autowired
    private Users_I usersI;
    public LoginOutput login (UserLogin_Input input){
        List<User> users = usersI.findByConditionEqual("email", input.getEmail());
        if (!users.isEmpty()){
            if (users.get(0).equals(input.getPassword())){
                return new LoginOutput(users.get(0).accountType, users.get(0));
            }
            else {
                return new LoginOutput(false);
            }
        }
        else {
            return new LoginOutput(false);
        }
    }

}
