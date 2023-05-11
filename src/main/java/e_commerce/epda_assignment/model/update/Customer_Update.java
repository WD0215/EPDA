package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.Customer;
import e_commerce.epda_assignment.model.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Customer_Update {
    private String id;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNO;
    private String imageURL;
    private String customerIC;
    private String email;
    private String password;

    public Customer toCustomer(Customer customer){
        customer.setCustomerIC(customerIC);
        customer.setCustomerPhoneNO(customerPhoneNO);
        customer.setUpdateTimestamp(LocalDateTime.now());
        customer.setCustomerLastName(customerLastName);
        customer.setCustomerFirstName(customerFirstName);
        if (imageURL != null) {
            customer.setImageURL(imageURL);
        }
        return customer;
    }

    public User toUser (User user) {
        if (email != null){
            user.setEmail(email);
        }
        if (password != null){
            user.setPassword(password);
        }
        return user;
    }

}
