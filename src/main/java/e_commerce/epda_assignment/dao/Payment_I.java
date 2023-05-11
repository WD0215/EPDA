package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Payment;

import java.util.List;

public interface Payment_I {
    boolean create(Payment input);
    Payment update(Payment input);
    boolean updateMultiple (List<Payment> list, String updateField, String updateValue);
    boolean delete(Payment input);
    List<Payment> getAllPayment ();
    List<Payment> findByConditionEqual (String whereField, Object whereValue);
    Payment getPaymentbyId(String id);
}
