package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.models.Address;
import com.example.demo.models.CategoryId;
import com.example.demo.models.Image;
import com.example.demo.models.ImageId;
import com.example.demo.models.Money;
import com.example.demo.models.Order;
import com.example.demo.models.OrderId;
import com.example.demo.models.OrderLineItem;
import com.example.demo.models.OrderLineItemId;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Payment;
import com.example.demo.models.PhoneNumber;
import com.example.demo.models.PostalCode;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.models.ProductOption;
import com.example.demo.models.ProductOptionId;
import com.example.demo.models.ProductOptionItem;
import com.example.demo.models.ProductOptionItemId;
import com.example.demo.models.Receiver;
import com.example.demo.models.User;
import com.example.demo.models.UserId;

import static com.example.demo.TestUtils.createOrderOptions;
import static com.example.demo.models.Role.ROLE_ADMIN;
import static com.example.demo.models.Role.ROLE_USER;

public class Fixtures {
    public static User user(String username) {
        if (username.equals("tester")) {
            return new User(new UserId("0BV000USR0001"), "tester@example.com",
                    "테스터", ROLE_USER);
        }

        if (username.equals("admin")) {
            return new User(new UserId("0BV000USR0002"), "admin@example.com",
                    "관리자", ROLE_ADMIN);
        }

        throw new NoSuchElementException("User - username: " + username);
    }

    public static Product product(String name) {
        if (name.equals("맨투맨")) {
            return new Product(
                    new ProductId("0BV000PRO0001"),
                    new CategoryId("0BV000CAT0001"),
                    List.of(
                            new Image(
                                    ImageId.generate(),
                                    "http://example.com/product-01.jpg"
                            )
                    ),
                    "맨투맨",
                    new Money(128_000L),
                    List.of(
                            productOption("색상"),
                            productOption("크기")
                    ),
                    "편하게 입을 수 있는 맨투맨");
        }

        if (name.equals("셔츠")) {
            return new Product(
                    new ProductId("0BV000PRO0002"),
                    new CategoryId("0BV000CAT0001"),
                    List.of(
                            new Image(
                                    ImageId.generate(),
                                    "http://example.com/product-02.jpg"
                            )
                    ),
                    "셔츠",
                    new Money(123_000L),
                    List.of(),
                    "Shirt");
        }

        throw new NoSuchElementException("Product - name: " + name);
    }

    private static ProductOption productOption(String name) {
        if (name.equals("색상")) {
            return new ProductOption(
                    new ProductOptionId("0BV000OPT0001"),
                    "색상",
                    List.of(
                            productOptionItem("Black"),
                            productOptionItem("White")
                    ));
        }

        if (name.equals("크기")) {
            return new ProductOption(
                    new ProductOptionId("0BV000OPT0002"),
                    "크기",
                    List.of(
                            productOptionItem("S"),
                            productOptionItem("M"),
                            productOptionItem("L")
                    ));
        }

        throw new NoSuchElementException("ProductOption - name: " + name);
    }

    private static ProductOptionItem productOptionItem(String name) {
        if (name.equals("Black")) {
            return new ProductOptionItem(
                    new ProductOptionItemId("0BV000ITEM001"),
                    "Black");
        }

        if (name.equals("White")) {
            return new ProductOptionItem(
                    new ProductOptionItemId("0BV000ITEM002"),
                    "White");
        }

        if (name.equals("S")) {
            return new ProductOptionItem(
                    new ProductOptionItemId("0BV000ITEM003"),
                    "S");
        }

        if (name.equals("M")) {
            return new ProductOptionItem(
                    new ProductOptionItemId("0BV000ITEM004"),
                    "M");
        }

        if (name.equals("L")) {
            return new ProductOptionItem(
                    new ProductOptionItemId("0BV000ITEM005"),
                    "L");
        }

        throw new NoSuchElementException("ProductOptionItem - name: " + name);
    }

    public static Receiver receiver(String name) {
        return new Receiver(
                name,
                new Address("서울 성동구 상원12길 34", "ㅇㅇㅇ호",
                        new PostalCode("04790")),
                new PhoneNumber("01012345678"));
    }

    public static Payment payment() {
        return new Payment("PaymentMerchantID", "PaymentTransactionID");
    }

    public static Order order(User user) {
        Product product = Fixtures.product("맨투맨");

        List<OrderLineItem> lineItems = List.of(
                new OrderLineItem(
                        OrderLineItemId.generate(),
                        product,
                        createOrderOptions(product, new int[]{0, 0}),
                        1
                )
        );

        Receiver receiver = Fixtures.receiver("홍길동");
        Payment payment = Fixtures.payment();

        OrderId orderId = OrderId.generate();

        return new Order(orderId, user.id(), lineItems, receiver, payment,
                OrderStatus.PAID);
    }
}
