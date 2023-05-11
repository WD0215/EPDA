package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.Payment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment_Input {
    private String orderID;
    private String sellerID;
    private String card;
    private String expireDate;
    private String cvc;
    private Double price;
    public Payment toPayment(){
        Payment payment = new Payment();
        payment.setOrderID(orderID);
        payment.setPrice(price);
        payment.setUpdateTimestamp(LocalDateTime.now());
        payment.setSellerID(sellerID);
        payment.setCard(card);
        payment.setExpireDate(expireDate);
        payment.setCvc(cvc);
        return payment;
    }

    public Payment_Input (String orderId,String card, String expireDate, String cvc, Double price,String sellerID){
        this.orderID = orderId;
        this.sellerID = sellerID;
        this.card = card;
        this.expireDate = expireDate;
        this.cvc = cvc;
        this.price = price;
    }
}
