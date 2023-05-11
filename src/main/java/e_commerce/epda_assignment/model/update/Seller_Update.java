package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Seller_Update {
    private String id;
    private String sellerFirstName;
    private String setCustomerLastName;
    private String sellerPhoneNO;
    private String imageURL;
    private String email;
    private String password;
    private Double wallet;

    public Seller toSeller(Seller seller){
        seller.setSellerPhoneNO(sellerPhoneNO);
        seller.setUpdateTimestamp(LocalDateTime.now());
        seller.setSellerLastName(setCustomerLastName);
        seller.setSellerFirstName(sellerFirstName);
        seller.setWallet(wallet);
        if (imageURL != null) {
            seller.setImageURL(imageURL);
        }
        return seller;
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
