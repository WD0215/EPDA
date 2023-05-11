package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.Seller;
import lombok.Data;

@Data
public class SellerRankingBySales {
    private Seller seller;
    private Double totalSales;

    public SellerRankingBySales (Seller seller,Double totalSales){
        this.seller = seller;
        this.totalSales = totalSales;
    }

}
