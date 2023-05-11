package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

@Data
public class Seller_Input {
    private String userID;
    private String sellerFirstName;
    private String sellerLastName;
    private String sellerPhoneNo;
    private String imageURL;
    private String sellerEmail;
    private String password;
    private Static.accType accountType = Static.accType.seller;
    private boolean isPrimary = true;
    private String unit;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Seller toSeller(){
        Seller seller = new Seller();
        seller.setSellerFirstName(sellerFirstName);
        seller.setSellerLastName(sellerLastName);
        seller.setUserID(userID);
        seller.setSellerPhoneNO(sellerPhoneNo);
        seller.setImageURL(imageURL);
        return seller;
    }
    public User toUser(){
        User user = new User();
        user.setAccountType(Static.accType.seller);
        user.setEmail(sellerEmail);
        user.setPassword(password);
        return user;
    }

    public Address toAddress_Input(){
        Address location = new Address();
        location.setUserID(this.userID);
        location.setPrimary(isPrimary);
        location.setCity(city);
        location.setState(state);
        location.setStreet(street);
        location.setCountry(country);
        location.setZipcode(zipcode);
        location.setUnit(unit);
        return location;
    }

}
