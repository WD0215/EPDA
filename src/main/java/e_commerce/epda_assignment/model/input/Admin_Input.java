package e_commerce.epda_assignment.model.input;
import e_commerce.epda_assignment.model.Admin;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Admin_Input {
    private String userID;
    private String adminFirstName;
    private String adminLastName;
    private String adminPhoneNO;
    private String adminEmail;
    private Static.accType accountType = Static.accType.admin;
    private String password;
    private String imageURL;
    private LocalDateTime updateTimestamp = LocalDateTime.now();

    public Admin toAdmin(){
        Admin admin = new Admin();
        admin.setAdminFirstName(adminFirstName);
        admin.setAdminLastName(adminLastName);
        admin.setAdminPhoneNO(adminPhoneNO);
        admin.setUserID(userID);
        admin.setImageURL(imageURL);
        admin.setAccountType(Static.accType.admin);
        admin.setUpdateTimestamp(updateTimestamp);
        return admin;
    }
    public User toUser(){
        User user = new User();
        user.setEmail(adminEmail);
        user.setPassword(password);
        user.setAccountType(Static.accType.admin);
        return user;
    }
}
