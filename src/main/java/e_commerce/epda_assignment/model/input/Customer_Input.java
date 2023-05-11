package e_commerce.epda_assignment.model.input;
import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Customer;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer_Input {
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNo;
    private String userID;
    private String customerEmail;
    private String password;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private Static.accType accountType = Static.accType.client;
    private boolean isPrimary = true;
    private String unit;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String imageURL;


    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setCustomerFirstName(customerFirstName);
        customer.setCustomerLastName(customerLastName);
        customer.setCustomerPhoneNO(customerPhoneNo);
        customer.setUserID(userID);
        customer.setAccountType(Static.accType.client);
        customer.setImageURL(imageURL);
        customer.setUpdateTimestamp(updateTimestamp);
        return customer;
    }
    public User toUser(){
        User user = new User();
        user.setEmail(customerEmail);
        user.setPassword(password);
        user.setAccountType(Static.accType.client);
        return user;
    }
    public Address toAddress_Input(){
        Address location = new Address();
        location.setUserID(userID);
        location.setPrimary(isPrimary);
        location.setCity(city);
        location.setState(state);
        location.setCountry(country);
        location.setZipcode(zipcode);
        location.setUnit(unit);
        location.setStreet(street);
        return location;
    }
}
