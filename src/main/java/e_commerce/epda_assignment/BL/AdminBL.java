package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Admin_I;
import e_commerce.epda_assignment.dao.Seller_I;
import e_commerce.epda_assignment.dao.Users_I;
import e_commerce.epda_assignment.model.Admin;
import e_commerce.epda_assignment.model.Seller;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.model.input.Admin_Input;
import e_commerce.epda_assignment.model.output.*;
import e_commerce.epda_assignment.model.update.Admin_Update;
import e_commerce.epda_assignment.utility.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminBL implements AdminBLI{
    @Autowired
    Admin_I adminI;
    @Autowired
    Users_I usersI;
    @Autowired
    Seller_I sellerI;
    @Autowired
    SellerBLI sellerBLI;
    @Autowired
    OrderBLI orderBLI;

    public Admin_Profile registerAdmin (Admin_Input input){
        //Upload Image, Get URL
        String url = "blabla";
        input.setImageURL(url);
        User user = usersI.create(input.toUser());
        if (user == null){
            return null;
        }
        Admin admin = adminI.create(input.toAdmin());
        if (admin == null){
            usersI.delete(user);
            return null;
        }
        return new Admin_Profile(user, admin);

    }
    public respondOutput updateAdmin (Admin_Update input){
        try {
            Admin admin = adminI.getAdminbyId(input.getId());
//            Admin admin1 = (Seller)updated.getUpdatedObject();
            if (admin == null) {
                return new respondOutput(false);
            }
            if (input.getEmail() != null || input.getPassword() != null) {
                usersI.update(input.toUser(usersI.getUserbyId(admin.getUserID())));
            }
            return new respondOutput(getAdminProfile(adminI.update(input.toAdmin(admin))));
        }
        catch (Exception e){
            return new respondOutput(false);
        }
    }

    public Admin_Profile getAdminProfile (Admin admin){
        User user = usersI.getUserbyId(admin.getUserID());
        return new Admin_Profile(user,admin);
    }
    public Admin_Profile getAdminProfileByAdminId (String adminId){
        Admin admin = adminI.getAdminbyId(adminId);
        return getAdminProfile(admin);
    }

    public boolean deleteAdmin (String adminId) {
        Admin admin = adminI.getAdminbyId(adminId);
        User user = usersI.getUserbyId(admin.getUserID());
        List<Boolean> result = new ArrayList<>();
        result.add(adminI.delete(admin));
        result.add(usersI.delete(user));
        return result.stream().allMatch(x->x);
    }

    public boolean approveRejectSeller (String sellerId, boolean approve){
        Seller seller = sellerI.getSellerbyId(sellerId);
        seller.setApproved(approve);
        return sellerI.update(seller) != null;
    }
    public List<Seller_Profile> viewAllSeller (){
        List<Seller_Profile> sellerProfiles =  sellerI.getAllSeller().stream().map(x-> sellerBLI.getSellerProfile(x)).collect(Collectors.toList());
        sellerProfiles.sort(Comparator.comparing(Seller_Profile::getUpdateTimestamp));
        return  sellerProfiles;
    }
    public List<Seller_Profile> viewAllRejectedSeller (){
        List<Seller_Profile> sellerProfiles =  sellerI.findByConditionEqual("isApproved", false).stream().map(x-> sellerBLI.getSellerProfile(x)).collect(Collectors.toList());
        sellerProfiles.sort(Comparator.comparing(Seller_Profile::getUpdateTimestamp));
        return  sellerProfiles;
    }
    public List<Seller_Profile> viewAllApprovedSeller (){
        List<Seller_Profile> sellerProfiles =  sellerI.findByConditionEqual("isApproved", true).stream().map(x-> sellerBLI.getSellerProfile(x)).collect(Collectors.toList());
        sellerProfiles.sort(Comparator.comparing(Seller_Profile::getUpdateTimestamp));
        return  sellerProfiles;
    }

    public List<OrderOutput> viewAllSuccessOrderBySellerId (String sellerID){
        return orderBLI.getAllOrderBySellerId(sellerID)
                .stream()
                .filter(x->x.getOrder().getOrderStatus() == Static.orderStatus.delivering
                        || x.getOrder().getOrderStatus() == Static.orderStatus.delivered)
                .collect(Collectors.toList());
    }

    public List<SellerRankingBySales> sellerSalesAnalysis (){
        List<Seller> sellers = sellerI.getAllSeller();
        List<SellerRankingBySales> ranking = new ArrayList<>();
        sellers.forEach(x->{
            ranking.add(new SellerRankingBySales(x, viewAllSuccessOrderBySellerId(x.getId()).stream().map(y-> y.getPayment()).map(y-> y.getPrice()).mapToDouble(Double::doubleValue).sum()));
        });
        ranking.sort(Comparator.comparingDouble(SellerRankingBySales::getTotalSales).reversed());
        return ranking;
    }




}
