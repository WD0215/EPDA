package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.dao.Cart_I;
import e_commerce.epda_assignment.dao.Customers_I;
import e_commerce.epda_assignment.model.Cart_Item;
import e_commerce.epda_assignment.model.input.CartItem_Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemBL implements  CartItemBLI {
    @Autowired
    Cart_I cartI;
    @Autowired
    Customers_I customersI;

    public boolean addToCart(CartItem_Input input) {
        return cartI.create(input.toCartItem());
    }
    public List<Cart_Item> getAllCartByCustomerId (String customerId) {
        return cartI.findByConditionEqual("customerID",customerId).stream().filter(x->x.isStatus()).collect(Collectors.toList());
    }
    public boolean deleteCart (String cartId) {
        return cartI.delete(cartI.getCartbyId(cartId));
    }

    }
