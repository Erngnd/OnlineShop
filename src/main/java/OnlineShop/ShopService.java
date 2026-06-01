package OnlineShop;

import java.util.Set;

public class ShopService {

    public static void checkout(ShoppingCart cart) {
        if (cart == null || cart.cartItem == null) {
            return;
        }
        for (CartItem item : cart.cartItem) {
            if (item == null || item.product == null) {
                continue;
            }
            for (Product product : item.product) {
                if (product == null) {
                    continue;
                }
                if (product.stockQuantity <= 0) {
                    throw new IllegalStateException("Item sold out: " + product.name);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("ShopService main started.");
        System.out.println("Use this application entry point to run or test the shop service.");
    }
}
