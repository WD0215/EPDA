package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Address_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressBL implements AddressBLI{
    @Autowired
    UserBLI userBLI;
    @Autowired
    Address_I addressI;

}
