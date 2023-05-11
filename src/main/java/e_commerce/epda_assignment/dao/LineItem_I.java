package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Line_Item;

import java.util.List;

public interface LineItem_I {
    boolean create(Line_Item input);
    boolean update(Line_Item input);
    boolean updateMultiple (List<Line_Item> list, String updateField, String updateValue);
    boolean delete(Line_Item input);
    Line_Item getLineItembyId(String id);
    List<Line_Item> getAllLineItem ();
    List<Line_Item> findByConditionEqual (String whereField, Object whereValue);
}
