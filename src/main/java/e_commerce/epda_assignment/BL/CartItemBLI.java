package e_commerce.epda_assignment.BL;

import e_commerce.epda_assignment.model.Cart_Item;
import e_commerce.epda_assignment.model.input.CartItem_Input;

import java.util.List;

public interface CartItemBLI {
    boolean addToCart(CartItem_Input input);
    List<Cart_Item> getAllCartByCustomerId (String customerId);
    boolean deleteCart (String cartId);
}
