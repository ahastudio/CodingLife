package com.example.demo.application;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.infrastructure.PaymentValidator;
import com.example.demo.models.Cart;
import com.example.demo.models.CartLineItem;
import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderLineItem;
import com.example.demo.models.OrderLineItemId;
import com.example.demo.models.OrderOption;
import com.example.demo.models.OrderOptionId;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Payment;
import com.example.demo.models.Product;
import com.example.demo.models.ProductOption;
import com.example.demo.models.ProductOptionId;
import com.example.demo.models.ProductOptionItem;
import com.example.demo.models.ProductOptionItemId;
import com.example.demo.models.Receiver;
import com.example.demo.models.UserId;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;

@Service
@Transactional
public class CreateOrderService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final PaymentValidator paymentValidator;

    public CreateOrderService(ProductRepository productRepository,
                              CartRepository cartRepository,
                              OrderRepository orderRepository,
                              PaymentValidator paymentValidator) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.paymentValidator = paymentValidator;
    }

    public Order createOrder(
            UserId userId, Receiver receiver, Payment payment) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow();

        List<OrderLineItem> lineItems = IntStream
                .range(0, cart.lineItemSize())
                .mapToObj(cart::lineItem)
                .map(this::createOrderLineItem)
                .toList();

        OrderId orderId = OrderId.generate();

        Order order = new Order(orderId, userId, lineItems, receiver, payment,
                OrderStatus.PAID);

        paymentValidator.validate(payment, order);

        orderRepository.save(order);

        cart.clear();

        return order;
    }

    private OrderLineItem createOrderLineItem(CartLineItem cartLineItem) {
        Product product = productRepository.findById(cartLineItem.productId())
                .orElseThrow();

        List<OrderOption> options = cartLineItem.optionIds().stream()
                .map(optionId ->
                        createOrderOption(cartLineItem, product, optionId))
                .toList();

        return new OrderLineItem(
                OrderLineItemId.generate(),
                product,
                options,
                cartLineItem.quantity()
        );
    }

    private static OrderOption createOrderOption(
            CartLineItem cartLineItem, Product product,
            ProductOptionId optionId) {
        ProductOptionItemId itemId = cartLineItem.optionItemId(optionId);

        ProductOption productOption = product.optionById(optionId);
        ProductOptionItem productOptionItem = productOption.itemById(itemId);

        return new OrderOption(
                OrderOptionId.generate(),
                productOption,
                productOptionItem
        );
    }
}
