package org.example.prj_banhcay.DTO.response;

import lombok.Builder;
import lombok.Data;
import org.example.prj_banhcay.Enum.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;

    private String customerName;
    private String phoneNumber;
    private String shippingAddress;
    private String email;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemResponse> items;

    private LocalDateTime createdAt;
}
