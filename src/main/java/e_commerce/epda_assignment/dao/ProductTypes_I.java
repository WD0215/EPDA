package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.ProductType;

import java.util.List;

public interface ProductTypes_I {
    boolean create(ProductType input);
    boolean update(ProductType input);
    boolean updateMultiple (List<ProductType> list, String updateField, String updateValue);
    boolean delete(ProductType input);
    ProductType getProductbyId(String id);
    List<ProductType> getAllProduct();
    List<ProductType> findByConditionEqual (String whereField, Object whereValue);
    List<ProductType> findByConditionLike (String whereField , String criteria);
    List<ProductType> searchProductBySeller (String sellerId , String name);
}
