package com.example.demo.application;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.infrastructure.PaymentValidator;
import com.example.demo.models.Address;
import com.example.demo.models.Cart;
import com.example.demo.models.CartLineItemOption;
import com.example.demo.models.Order;
import com.example.demo.models.Payment;
import com.example.demo.models.PhoneNumber;
import com.example.demo.models.PostalCode;
import com.example.demo.models.Product;
import com.example.demo.models.Receiver;
import com.example.demo.models.UserId;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;

import static com.example.demo.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateOrderServiceTest {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private PaymentValidator paymentValidator;

    private CreateOrderService createOrderService;

    private Product product;
    private Set<CartLineItemOption> options;
    private int quantity;

    private Receiver receiver;
    private Payment payment;

    @BeforeEach
    void setUpMockObjects() {
        productRepository = mock(ProductRepository.class);
        cartRepository = mock(CartRepository.class);
        orderRepository = mock(OrderRepository.class);
        paymentValidator = mock(PaymentValidator.class);

        createOrderService = new CreateOrderService(
                productRepository, cartRepository, orderRepository,
                paymentValidator);
    }

    @BeforeEach
    void setUpFixtures() {
        product = Fixtures.product("맨투맨");

        options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        quantity = 1;

        receiver = new Receiver(
                "홍길동",
                new Address(
                        "서울특별시 성동구 상원12길 34",
                        "ㅇㅇㅇ호",
                        new PostalCode("04790")
                ),
                new PhoneNumber("01012345678")
        );

        payment = new Payment(
                "ORDER-20230101-00000001",
                "123456789012"
        );
    }

    @Test
    void createOrder() {
        UserId userId = UserId.generate();

        Cart cart = new Cart(userId);
        cart.addProduct(product, options, quantity);

        given(cartRepository.findByUserId(userId))
                .willReturn(Optional.of(cart));

        given(productRepository.findById(product.id()))
                .willReturn(Optional.of(product));

        Order order = createOrderService.createOrder(userId, receiver, payment);

        assertThat(order.lineItemSize()).isEqualTo(1);

        verify(orderRepository).save(order);
    }
}
