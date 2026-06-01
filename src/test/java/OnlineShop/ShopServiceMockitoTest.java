package OnlineShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShopServiceMockitoTest {

    @Mock
    private Producer producer;

    @Test
    void directProducerAvailabilityReturnsBlueWhenRedIsNotAvailable() {
        Product product = new Product();
        product.productID = 1;
        product.name = "Red T-Shirt";

        when(producer.getAvailableColor(product, "red")).thenReturn(Optional.of("blue"));

        String result = ShopService.orderProductWithDirectAvailability(product, "red", producer);

        assertEquals("The item is only available in blue instead of red.", result);
        verify(producer).getAvailableColor(product, "red");
        verify(producer, never()).requestAlternativeColor(product, "red");
    }

    @Test
    void producerReportsNoRedThenShopRequestsAlternativeBlue() {
        Product product = new Product();
        product.productID = 2;
        product.name = "Red Hoodie";

        when(producer.getAvailableColor(product, "red")).thenReturn(Optional.empty());
        when(producer.requestAlternativeColor(product, "red")).thenReturn(Optional.of("blue"));

        String result = ShopService.orderProductWithAlternativeRequest(product, "red", producer);

        assertEquals("The item is only available in blue instead of red.", result);
        verify(producer).getAvailableColor(product, "red");
        verify(producer).requestAlternativeColor(product, "red");
    }
}
