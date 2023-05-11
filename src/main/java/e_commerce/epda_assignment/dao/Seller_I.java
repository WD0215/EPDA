package e_commerce.epda_assignment.dao;
import e_commerce.epda_assignment.model.Seller;

import java.util.List;

public interface Seller_I {
    Seller create(Seller input);
    Seller update(Seller input);
    boolean delete(Seller input);
    boolean updateMultiple (List<Seller> list, String updateField, String updateValue);
    Seller getSellerbyId(String id);
    List<Seller> getAllSeller ();
    List<Seller> findByConditionEqual (String whereField, Object whereValue);
}
