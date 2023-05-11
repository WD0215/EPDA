package e_commerce.epda_assignment.BL;


import e_commerce.epda_assignment.dao.*;
import e_commerce.epda_assignment.model.Line_Item;
import e_commerce.epda_assignment.model.Order;
import e_commerce.epda_assignment.model.Payment;
import e_commerce.epda_assignment.model.input.Order_Input;
import e_commerce.epda_assignment.model.output.OrderOutput;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Payment_Update;
import e_commerce.epda_assignment.utility.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderBL implements OrderBLI {
    @Autowired
    Order_I orderI;
    @Autowired
    LineItem_I lineItemI;
    @Autowired
    ProductTypes_I productTypesI;
    @Autowired
    Seller_I sellerI;
    @Autowired
    Payment_I paymentI;
    @Autowired
    SellerBLI sellerBLI;
    @Autowired
    Rating_I ratingI;

    public respondOutput createOrder (Order_Input input){
        Order order = orderI.create(input.toOrder());
        if (order == null) {
            return new respondOutput(false);
        }
        List<Boolean> result = new ArrayList<>();
        input.toLineItem(order.getId()).forEach(x-> {
            result.add(lineItemI.create(x));
        });
        if (result.stream().anyMatch(x->!x)){
            orderI.delete(order);
            return new respondOutput(false);
        }else {
            if (paymentI.create(input.toPaymentInput(order.getId(),getOrderSellerId(input.toLineItem(order.getId()))).toPayment())){
                return new respondOutput(getOrderDetail(order));
            }
            else {
                return new respondOutput(false);
            }
        }
    }
    public OrderOutput getOrderDetail (String orderId) {
        return getOrderDetail(orderI.getOrderbyId(orderId));
    }

    public OrderOutput getOrderDetail (Order order) {
        List<Line_Item> lineItems = lineItemI.findByConditionEqual("orderID", order.getId());
        return new OrderOutput(order,lineItems, paymentI.findByConditionEqual("orderID", order.getId()).get(0),ratingI.findByConditionEqual("orderID",order.getId()));
    }
    public String getOrderSellerId (String orderId){
        return getOrderSellerId(getOrderDetail(orderId));
    }

    public String getOrderSellerId (List<Line_Item> output){
        String sellerId = output
                .stream()
                .map(x-> productTypesI.getProductbyId(x.getProductTypeId()))
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList()).get(0).getSellerID();
        return sellerId;
    }
    public String getOrderSellerId (OrderOutput output){
        String sellerId = output.getItems()
                .stream()
                .map(x-> productTypesI.getProductbyId(x.getProductTypeId()))
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList()).get(0).getSellerID();
        return sellerId;
    }
    public respondOutput makePayment (Payment_Update input){
        Payment payment = paymentI.getPaymentbyId(input.getPaymentID());
        Order order = orderI.getOrderbyId(payment.getOrderID());
        order.setOrderStatus(Static.orderStatus.awaitingConfirmation);
        orderI.update(order);
        return new respondOutput(paymentI.update(input.toPayment(payment)));
    }
    public List<OrderOutput> getAllOrderByCustomerId (String customerId) {
        List<Order> orders = orderI.findByConditionEqual("customerID", customerId);
        List<OrderOutput> result = new ArrayList<>();
        orders.forEach(x-> {
            result.add(getOrderDetail(x));
        });
        return result;
    }

    public List<OrderOutput> getAllOrderBySellerId (String sellerId){
        List<Order> orders = paymentI.findByConditionEqual("sellerID",sellerId)
                .stream()
                .map(x-> orderI.getOrderbyId(x.getOrderID()))
                .collect(Collectors.toList());
        List<OrderOutput> result = new ArrayList<>();
        orders.forEach(x-> {
            result.add(getOrderDetail(x));
        });
        return result;
    }

    public respondOutput updateOrderStatusBySeller (String sellerId, String orderId, boolean confirm){
        Order order = orderI.getOrderbyId(orderId);
        if (!confirm){
            order.setOrderStatus(Static.orderStatus.rejected);
            orderI.update(order);
            return new respondOutput(false);
        }else {
            Payment payment = paymentI.findByConditionEqual("orderID", orderId).get(0);
            sellerBLI.addAmountToWallet(sellerId, payment.getPrice());
            order.setOrderStatus(Static.orderStatus.delivering);
        }
        return new respondOutput(getOrderDetail(orderI.update(order)));
    }

}
