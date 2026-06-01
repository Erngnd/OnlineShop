package OnlineShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class ShopServiceTest {

    @Test
    void checkoutThrowsWhenCartContainsSoldOutProduct() {
        Product soldOutProduct = new Product();
        soldOutProduct.productID = 1;
        soldOutProduct.name = "Wireless Mouse";
        soldOutProduct.description = "High-precision mouse for office use";
        soldOutProduct.price = 29.99;
        soldOutProduct.stockQuantity = 0;

        CartItem cartItem = new CartItem();
        cartItem.cartItemID = 1;
        cartItem.quantity = 1;
        cartItem.unitPrice = soldOutProduct.price;
        cartItem.product = Set.of(soldOutProduct);

        ShoppingCart cart = new ShoppingCart();
        cart.cartID = 1;
        cart.cartItem = Set.of(cartItem);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> ShopService.checkout(cart));

        assertEquals("Item sold out: Wireless Mouse", exception.getMessage());
    }
}
