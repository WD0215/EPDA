package e_commerce.epda_assignment.dao;

import e_commerce.epda_assignment.model.Rating;

import java.util.List;

public interface Rating_I {
    Rating create(Rating input);

    boolean update(Rating input);

    boolean updateMultiple (List<Rating> list, String updateField, String updateValue);

    boolean delete(Rating input);

    Rating getRatingbyId(String id);
    List<Rating> getAllRating ();

    List<Rating> findByConditionEqual (String whereField, Object whereValue);
}
