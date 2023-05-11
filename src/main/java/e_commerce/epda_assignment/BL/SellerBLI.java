package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.input.Seller_Input;
import e_commerce.epda_assignment.model.output.Seller_Profile;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Seller_Update;
import e_commerce.epda_assignment.utility.Static;

public interface SellerBLI {
    Seller_Profile registerSeller (Seller_Input input);
    respondOutput updateSeller (Seller_Update input);
    Seller_Profile getSellerProfile (Seller seller);
    boolean deleteSeller (String sellerId);
    Static.walletWithrawalStatus addAmountToWallet (String sellerId, Double amount);
    Static.walletWithrawalStatus subtractAmountfromWallet (String sellerId, Double amount);
}
