package e_commerce.epda_assignment.dao;
import e_commerce.epda_assignment.model.Admin;
import java.util.List;
public interface Admin_I {
    Admin create(Admin input);
    Admin update(Admin input);
    boolean updateMultiple (List<Admin> list, String updateField, String updateValue);
    boolean delete(Admin input);
    Admin getAdminbyId(String id);
    List<Admin> getAllAdmin ();
    List<Admin> findByConditionEqual (String whereField, Object whereValue);
}
