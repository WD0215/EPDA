package e_commerce.epda_assignment.controller;

import e_commerce.epda_assignment.dao.Cart_I;
import e_commerce.epda_assignment.dao.Users_I;
import e_commerce.epda_assignment.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class control {
    @Autowired
    Users_I users;
    @Autowired
    Cart_I cart;
    @Autowired
    public control (Users_I user/*,@Qualifier("emf") EntityManagerFactory emf,@Qualifier("emf1") EntityManagerFactory emf1*/){
        users = user;
        Customer customer = new Customer();
        System.out.println("hi");
    }
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String test(){
//            Customer Rating
//        Rating_Input customerInput = new Rating_Input();
//        customerInput.setUserId("asdasd");
//        customerInput.setOrderID("saddasdasd");
//        customerInput.setDescription("u very ugly");
//        customerInput.setRatingValue(5);
//        customerInput.setProductTypeID("asdasdasd");

        //Seller Rating
//        Rating_Input sellerInput = new Rating_Input();
//        sellerInput.setUserId("asdasd");
//        sellerInput.setOrderID("saddasdasd");
//        sellerInput.setProductTypeID("asdasdasd");
//        sellerInput.setDescription("u very ugly");
//        sellerInput.setSubReviewOfId("customerReview.getId");





        return "hi im sohai";
    }
}
