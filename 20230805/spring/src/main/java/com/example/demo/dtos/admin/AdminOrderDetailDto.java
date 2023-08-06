package com.example.demo.dtos.admin;

import java.util.List;

import com.example.demo.dtos.OrderLineItemDto;
import com.example.demo.models.Address;
import com.example.demo.models.Payment;
import com.example.demo.models.Receiver;

public record AdminOrderDetailDto(
        String id,
        UserDto orderer,
        String title,
        List<OrderLineItemDto> lineItems,
        Long totalPrice,
        ReceiverDto receiver,
        PaymentDto payment,
        String status,
        String orderedAt
) {
    public record UserDto(
            String name
    ) {
    }

    public record ReceiverDto(
            String name,
            String address1,
            String address2,
            String postalCode,
            String phoneNumber
    ) {
        public static ReceiverDto of(Receiver receiver) {
            Address address = receiver.address();
            return new ReceiverDto(
                    receiver.name(),
                    address.address1(),
                    address.address2(),
                    address.postalCode().toString(),
                    receiver.phoneNumber().toString()
            );
        }
    }

    public record PaymentDto(
            String merchantId,
            String transactionId
    ) {
        public static PaymentDto of(Payment payment) {
            return new PaymentDto(
                    payment.merchantId(),
                    payment.transactionId()
            );
        }
    }
}
