package e_commerce.epda_assignment.model.input;

import e_commerce.epda_assignment.model.Cart_Item;
import lombok.Data;

@Data
public class CartItem_Input {
    private String customerID;
    private String productTypeID;
    private int quantity;
    private Double price;
    public Cart_Item toCartItem (){
        Cart_Item cartItem = new Cart_Item();
        cartItem.setPrice(price);
        cartItem.setQuantity(quantity);
        cartItem.setCustomerID(customerID);
        cartItem.setProductTypeID(productTypeID);
        return cartItem;
    }
}
