package OnlineShop;

import java.util.Optional;
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

    public static String orderProductWithDirectAvailability(Product product, String requestedColor, Producer producer) {
        Optional<String> availableColor = producer.getAvailableColor(product, requestedColor);
        if (availableColor.isPresent()) {
            String actualColor = availableColor.get();
            if (actualColor.equalsIgnoreCase(requestedColor)) {
                return "Item available in " + requestedColor + ".";
            }
            return unavailableColorMessage(requestedColor, actualColor);
        }
        return "Requested item is not available.";
    }

    public static String orderProductWithAlternativeRequest(Product product, String requestedColor, Producer producer) {
        Optional<String> availableColor = producer.getAvailableColor(product, requestedColor);
        if (availableColor.isPresent()) {
            String actualColor = availableColor.get();
            if (actualColor.equalsIgnoreCase(requestedColor)) {
                return "Item available in " + requestedColor + ".";
            }
            return unavailableColorMessage(requestedColor, actualColor);
        }
        Optional<String> alternativeColor = producer.requestAlternativeColor(product, requestedColor);
        return alternativeColor.map(color -> unavailableColorMessage(requestedColor, color)).orElse("Requested item is not available.");
    }

    private static String unavailableColorMessage(String requestedColor, String availableColor) {
        return "The item is only available in " + availableColor + " instead of " + requestedColor + ".";
    }

    public static void main(String[] args) {
        System.out.println("ShopService main started.");
        System.out.println("Use this application entry point to run or test the shop service.");
    }
}
