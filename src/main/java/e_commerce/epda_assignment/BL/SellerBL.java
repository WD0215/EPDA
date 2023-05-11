package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Address_I;
import e_commerce.epda_assignment.dao.Seller_I;
import e_commerce.epda_assignment.dao.Users_I;
import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.model.input.Seller_Input;
import e_commerce.epda_assignment.model.output.Seller_Profile;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Seller_Update;
import e_commerce.epda_assignment.utility.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerBL implements SellerBLI{
    @Autowired
    Seller_I sellerI;
    @Autowired
    Users_I usersI;
    @Autowired
    Address_I addressI;
    public Seller_Profile registerSeller (Seller_Input input){
        //Upload Image, Get URL
        String url = "blabla";
        input.setImageURL(url);
        User user = usersI.create(input.toUser());
        if (user == null){
            return null;
        }
        input.setUserID(user.getId());
        Address address = addressI.create(input.toAddress_Input());
        if (address == null){
            usersI.delete(user);
            return null;
        }
        Seller seller = sellerI.create(input.toSeller());
        if (seller == null){
            usersI.delete(user);
            addressI.delete(address);
            return null;
        }
        return new Seller_Profile(user, seller, address);

    }
    public respondOutput updateSeller (Seller_Update input){
        try {
            Seller seller = sellerI.getSellerbyId(input.getId());
//            Seller seller1 = (Seller)updated.getUpdatedObject();
            if (seller == null) {
                return new respondOutput(false);
            }
            if (input.getEmail() != null || input.getPassword() != null) {
                usersI.update(input.toUser(usersI.getUserbyId(seller.getUserID())));
            }
            return new respondOutput(getSellerProfile(sellerI.update(input.toSeller(seller))));
        }
        catch (Exception e){
            return new respondOutput(false);
        }
    }

    public Seller_Profile getSellerProfile (Seller seller){
        User user = usersI.getUserbyId(seller.getUserID());
        return new Seller_Profile(user,seller,addressI.findByConditionEqual("userID", user.getId()).get(0));
    }
    public Seller_Profile getSellerProfileBySellerId (String sellerId){
        Seller seller = sellerI.getSellerbyId(sellerId);
        return getSellerProfile(seller);
    }

    public boolean deleteSeller (String sellerId) {
        Seller seller = sellerI.getSellerbyId(sellerId);
        User user = usersI.getUserbyId(seller.getUserID());
        List<Address> addresses = addressI.findByConditionEqual("userID", user.getId());
        List<Boolean> result = new ArrayList<>();
        result.add(sellerI.delete(seller));
        result.add(usersI.delete(user));
        result.addAll(addresses.stream().map(x-> addressI.delete(x)).collect(Collectors.toList()));
        return result.stream().allMatch(x->x);
    }

    public Static.walletWithrawalStatus addAmountToWallet (String sellerId, Double amount){
        Seller seller = sellerI.getSellerbyId(sellerId);
        seller.setWallet(seller.getWallet() + amount);
        if(sellerI.update(seller) != null){
            return Static.walletWithrawalStatus.approved;
        }else {
            return Static.walletWithrawalStatus.error;
        }
    }
    public Static.walletWithrawalStatus subtractAmountfromWallet (String sellerId, Double amount){
        Seller seller = sellerI.getSellerbyId(sellerId);
        if (amount > seller.getWallet()){
            return Static.walletWithrawalStatus.withraw_more_than_balance;
        }
        seller.setWallet(seller.getWallet() - amount);
        if(sellerI.update(seller) != null){
            return Static.walletWithrawalStatus.approved;
        }else {
            return Static.walletWithrawalStatus.error;
        }
    }


}
