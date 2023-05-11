package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.Admin;
import e_commerce.epda_assignment.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Admin_Update {
    private String id;
    private String adminFirstName;
    private String adminLastName;
    private String adminPhoneNO;
    private String imageURL;
    private String email;
    private String password;

    public Admin toAdmin(Admin admin){
        admin.setAdminFirstName(adminFirstName);
        admin.setUpdateTimestamp(LocalDateTime.now());
        admin.setAdminLastName(adminLastName);
        admin.setAdminFirstName(adminFirstName);
        if (imageURL != null) {
            admin.setImageURL(imageURL);
        }
        return admin;
    }

    public User toUser (User user) {
        if (email != null){
            user.setEmail(email);
        }
        if (password != null){
            user.setPassword(password);
        }
        return user;
    }
}
