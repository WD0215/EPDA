package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.Line_Item;
import e_commerce.epda_assignment.model.Order;
import e_commerce.epda_assignment.model.Payment;
import e_commerce.epda_assignment.model.Rating;
import lombok.Data;

import java.util.List;

@Data
public class OrderOutput {
    private Order order;
    private List<Line_Item> items;
    private Payment payment;
    private String sellerId;
    private List<Rating> rating;
    public OrderOutput (Order order, List<Line_Item> items, Payment payment, List<Rating> rating){
        this.order = order;
        this.items = items;
        this.payment = payment;
        this.rating = rating;
        this.sellerId = payment.getSellerID();
    }
}
