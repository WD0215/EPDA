package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Order;

import java.util.List;

public interface Order_I {
    Order create(Order input);

    Order update(Order input);

    boolean updateMultiple (List<Order> list, String updateField, String updateValue);

    boolean delete(Order input);
    List<Order> getAllOrder();
    Order getOrderbyId(String id);
    List<Order> findByConditionEqual (String whereField, Object whereValue);
}
