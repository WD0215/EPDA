package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Customer;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Customer_Profile {
    private String customerID;
    private String userID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNO;
    private String customerEmail;
    private String imageURL;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private String customerIC;
    private Static.accType accountType = Static.accType.client;
    private List<Address> address;
    public Customer_Profile (){

    }
    public Customer_Profile(User user, Customer customer,Address address1){
        customerID = customer.getId();
        userID = customer.getUserID();
        customerFirstName = customer.getCustomerFirstName();
        customerLastName = customer.getCustomerLastName();
        customerPhoneNO = customer.getCustomerPhoneNO();
        imageURL = customer.getImageURL();
        updateTimestamp = customer.getUpdateTimestamp();
        customerIC = customer.getCustomerIC();
        customerEmail = user.getEmail();
        accountType = user.getAccountType();
        address = new ArrayList<>(){{add(address1);}};
    }
    public Customer_Profile(User user, Customer customer,List<Address> address1){
        customerID = customer.getId();
        userID = customer.getUserID();
        customerFirstName = customer.getCustomerFirstName();
        customerLastName = customer.getCustomerLastName();
        customerPhoneNO = customer.getCustomerPhoneNO();
        imageURL = customer.getImageURL();
        updateTimestamp = customer.getUpdateTimestamp();
        customerIC = customer.getCustomerIC();
        customerEmail = user.getEmail();
        accountType = user.getAccountType();
        address = address1;
    }
}
