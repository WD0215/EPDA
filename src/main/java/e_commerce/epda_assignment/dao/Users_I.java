package e_commerce.epda_assignment.dao;
import e_commerce.epda_assignment.model.User;

import java.util.List;

public interface Users_I {
    User create(User input);
    User update(User user);
    boolean updateMultiple (List<Users> list, String updateField, String updateValue);
    boolean delete(User user);
    User getUserbyId(String id);
    List<User> getAllUser();
    List<User> findByConditionEqual (String whereField, Object whereValue);
}
