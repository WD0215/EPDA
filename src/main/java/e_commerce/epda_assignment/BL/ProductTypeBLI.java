package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.output.ProductOutput;

import java.util.List;

public interface ProductTypeBLI {
    List<ProductOutput> getAllProductBySellerId (String sellerId);
}
