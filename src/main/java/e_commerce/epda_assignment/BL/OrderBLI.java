package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.Line_Item;
import e_commerce.epda_assignment.model.Order;
import e_commerce.epda_assignment.model.input.Order_Input;
import e_commerce.epda_assignment.model.output.OrderOutput;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Payment_Update;

import java.util.List;

public interface OrderBLI {
    respondOutput createOrder (Order_Input input);
    OrderOutput getOrderDetail (String orderId);
    OrderOutput getOrderDetail (Order order);
    String getOrderSellerId (String orderId);
    String getOrderSellerId (List<Line_Item> output);
    String getOrderSellerId (OrderOutput output);
    respondOutput makePayment (Payment_Update input);
    List<OrderOutput> getAllOrderBySellerId (String sellerId);
    List<OrderOutput> getAllOrderByCustomerId (String customerId);
}
