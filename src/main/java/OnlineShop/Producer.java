package OnlineShop;

import java.util.Optional;

public interface Producer {

    Optional<String> getAvailableColor(Product product, String requestedColor);

    Optional<String> requestAlternativeColor(Product product, String requestedColor);
}
