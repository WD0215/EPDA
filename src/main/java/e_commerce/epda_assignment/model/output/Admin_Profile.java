package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.Admin;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Admin_Profile {
    private String adminID;
    private String userID;
    private String adminFirstName;
    private String adminLastName;
    private String adminPhoneNO;
    private String adminEmail;
    private Static.accType accountType = Static.accType.admin;
    private String imageURL;
    private LocalDateTime updateTimestamp = LocalDateTime.now();

    public Admin_Profile(User user, Admin admin) {
        adminID = admin.getId();
        userID = admin.getUserID();
        adminFirstName = admin.getAdminFirstName();
        adminLastName = admin.getAdminLastName();
        adminPhoneNO = admin.getAdminPhoneNO();
        imageURL = admin.getImageURL();
        updateTimestamp = admin.getUpdateTimestamp();
        adminEmail = user.getEmail();
        accountType = user.getAccountType();
    }
}

