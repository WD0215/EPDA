package e_commerce.epda_assignment.dao;
import e_commerce.epda_assignment.model.Address;

import java.util.List;

public interface Address_I {
    Address create(Address input);
    Address update(Address input);
    boolean updateMultiple (List<Address> list, String updateField, String updateValue);
    boolean delete(Address input);
    Address getAddressbyId(String id);
    List<Address> getAllAddress ();
    List<Address> findByConditionEqual (String whereField, Object whereValue);
}
