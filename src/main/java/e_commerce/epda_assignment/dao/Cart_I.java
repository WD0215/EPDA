package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Cart_Item;
import java.util.List;

public interface Cart_I {
    boolean create(Cart_Item input);
    boolean update(Cart_Item input);
    boolean updateMultiple (List<Cart_Item> list, String updateField, String updateValue);
    boolean delete(Cart_Item input);
    List<Cart_Item> getAllCart ();
    Cart_Item getCartbyId(String id);
    List<Cart_Item> findByConditionEqual (String whereField, Object whereValue);
}
