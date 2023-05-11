package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Address_I;
import e_commerce.epda_assignment.dao.Customers_I;
import e_commerce.epda_assignment.dao.Users_I;
import e_commerce.epda_assignment.model.Address;
import e_commerce.epda_assignment.model.Customer;
import e_commerce.epda_assignment.model.User;
import e_commerce.epda_assignment.model.input.Customer_Input;
import e_commerce.epda_assignment.model.output.Customer_Profile;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Customer_Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBL implements CustomerBLI{
    @Autowired
    Customers_I customersI;
    @Autowired
    Users_I usersI;
    @Autowired
    Address_I addressI;
    public Customer_Profile registerCustomer (Customer_Input input){
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
        Customer customer = customersI.create(input.toCustomer());
        if (customer == null){
            usersI.delete(user);
            addressI.delete(address);
            return null;
        }
        return new Customer_Profile(user, customer, address);

    }
    public respondOutput updateCustomer (Customer_Update input){
        try {
            Customer customer = customersI.getCustomerbyId(input.getId());
//            Customer customer1 = (Customer)updated.getUpdatedObject();
            if (customer == null) {
                return new respondOutput(false);
            }
            if (input.getEmail() != null || input.getPassword() != null) {
                usersI.update(input.toUser(usersI.getUserbyId(customer.getUserID())));
            }
            return new respondOutput(getCustomerProfile(customersI.update(input.toCustomer(customer))));
        }
        catch (Exception e){
            return new respondOutput(false);
        }
    }

    private Customer_Profile getCustomerProfile (Customer customer){
        User user = usersI.getUserbyId(customer.getUserID());
        return new Customer_Profile(user,customer,addressI.findByConditionEqual("userID", user.getId()));
    }
    public Customer_Profile getCustomerProfileByCustomerId (String customerId){
        Customer customer = customersI.getCustomerbyId(customerId);
        return getCustomerProfile(customer);
    }

    public boolean deleteCustomer (String customerId) {
        Customer customer = customersI.getCustomerbyId(customerId);
        User user = usersI.getUserbyId(customer.getUserID());
        List<Address> addresses = addressI.findByConditionEqual("userID", user.getId());
        List<Boolean> result = new ArrayList<>();
        result.add(customersI.delete(customer));
        result.add(usersI.delete(user));
        result.addAll(addresses.stream().map(x-> {
            return addressI.delete(x);
        }).collect(Collectors.toList()));
        return result.stream().allMatch(x->!x);
    }


}
