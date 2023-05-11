package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.Admin;
import e_commerce.epda_assignment.model.input.Admin_Input;
import e_commerce.epda_assignment.model.output.Admin_Profile;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Admin_Update;

public interface AdminBLI {
    Admin_Profile registerAdmin (Admin_Input input);
    respondOutput updateAdmin (Admin_Update input);
    Admin_Profile getAdminProfile (Admin admin);
    boolean deleteAdmin (String adminId);
}
