package e_commerce.epda_assignment.utility;

public class Static {
    public enum accType {
        client,
        seller,
        admin
    }
    public enum paymentType {
        pending,
        paid
    }
    public enum orderStatus{
        pendingPayment,
        awaitingConfirmation,
        delivering,
        delivered,
        rejected
    }
    public enum walletWithrawalStatus {
        approved,
        error,
        withraw_more_than_balance,
    }

    public static Double deliveryCost = 3.00;
}
