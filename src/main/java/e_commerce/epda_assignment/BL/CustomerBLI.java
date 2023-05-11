package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.input.Customer_Input;
import e_commerce.epda_assignment.model.output.Customer_Profile;
import e_commerce.epda_assignment.model.output.respondOutput;
import e_commerce.epda_assignment.model.update.Customer_Update;

public interface CustomerBLI {
    Customer_Profile registerCustomer (Customer_Input input);
    boolean deleteCustomer (String customerId);
    respondOutput updateCustomer (Customer_Update input);
    Customer_Profile getCustomerProfileByCustomerId (String customerId);
}
