package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.utility.Static;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Seller_Profile {
    private String sellerID;
    private String userID;
    private String sellerEmail;
    private String sellerFirstName;
    private String sellerLastName;
    private String imageURL;
    private String sellerPhoneNO;
    private Static.accType accountType = Static.accType.seller;
    private LocalDateTime updateTimestamp = LocalDateTime.now();
    private List<Address> address;
    private Double wallet;
    private boolean isApproved = false;

    public Seller_Profile (){

    }
    public Seller_Profile(User user, Seller seller, Address address2){
        sellerID = seller.getId();
        userID = seller.getUserID();
        sellerFirstName = seller.getSellerFirstName();
        sellerLastName = seller.getSellerLastName();
        sellerPhoneNO = seller.getSellerPhoneNO();
        imageURL = seller.getImageURL();
        updateTimestamp = seller.getUpdateTimestamp();
        sellerEmail = user.getEmail();
        accountType = user.getAccountType();
        address= new ArrayList<>(){{add(address2);}};
        wallet = seller.getWallet();
        isApproved = seller.isApproved();
    }
}