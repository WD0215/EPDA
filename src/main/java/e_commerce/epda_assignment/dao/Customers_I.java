package e_commerce.epda_assignment.dao;
import e_commerce.epda_assignment.model.Customer;

import java.util.List;

public interface Customers_I {
    Customer create(Customer input);
    Customer update(Customer customer);
    boolean updateMultiple (List<Customer> list, String updateField, String updateValue);
    boolean delete(Customer input);
    Customer getCustomerbyId(String id);
    List<Customer> getAllCustomer();
    List<Customer> findByConditionEqual (String whereField, Object whereValue);
}
