package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.Line_Item;
import e_commerce.epda_assignment.model.Order;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order_Input {
    private String paymentID;
    private String customerID;
    private String estimateDeliveryTimestamp;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private List<LineItem_Input> items;
    private String addressID;

    public Order toOrder() {
        Order order = new Order();
        order.setOrderStatus(Static.orderStatus.pendingPayment);
        order.setCustomerID(customerID);
        order.setDeliveryCost(Double.valueOf(items.size()) * Static.deliveryCost);
        order.setAddressID(addressID);
        return order;
    }

    public List<Line_Item> toLineItem(String orderID) {
        List<Line_Item> items = new ArrayList<>();
        this.items.forEach(x -> {
            Line_Item lineItem = new Line_Item();
            lineItem.setOrderID(orderID);
            lineItem.setQuantity(x.getQuantity());
            lineItem.setPrice(x.getPrice() * x.getQuantity());
            lineItem.setUnitPrice(x.getPrice());
            lineItem.setProductTypeId(x.getProductTypeID());
            items.add(lineItem);
        });
        return items;
    }

    public Payment_Input toPaymentInput (String orderId, String sellerId){
        Double price = toLineItem(orderId).stream().map(x-> x.getPrice()).mapToDouble(Double::doubleValue).sum() + (Double.valueOf(items.size()) * Static.deliveryCost);
        return new Payment_Input(orderId,null,null,null,price,sellerId);
    }
}

