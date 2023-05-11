package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.Payment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment_Update {
    private String paymentID;
    private String card;
    private String expireDate;
    private String cvc;
    private Double price;

    public Payment toPayment(Payment payment ){
        payment.setPrice(price);
        payment.setUpdateTimestamp(LocalDateTime.now());
        payment.setCard(card);
        payment.setExpireDate(expireDate);
        payment.setCvc(cvc);
        return payment;
    }
}
