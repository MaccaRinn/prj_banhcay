package org.example.prj_banhcay.service;


import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.prj_banhcay.DTO.request.CreateOrderRequest;
import org.example.prj_banhcay.DTO.response.OrderItemResponse;
import org.example.prj_banhcay.DTO.response.OrderResponse;
import org.example.prj_banhcay.model.Order;
import org.example.prj_banhcay.model.OrderItem;
import org.example.prj_banhcay.model.Product;
import org.example.prj_banhcay.repo.OrderItemsRepo;
import org.example.prj_banhcay.repo.OrderRepo;
import org.example.prj_banhcay.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderItemsRepo orderItemsRepo;

    private static final String CART = "cart";

    @SuppressWarnings("unchecked")
    private Map<Long, Integer> getCart(HttpSession session) {
        Map<Long, Integer> cart =
                (Map<Long, Integer>) session.getAttribute(CART);

        return cart == null ? new HashMap<>() : cart;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request,
                                     HttpSession session) {

        Map<Long, Integer> cart = getCart(session);

        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .shippingAddress(request.getShippingAddress())
                .email(request.getEmail())
                .status(org.example.prj_banhcay.Enum.OrderStatus.PENDING)
                .build();

        orderRepo.save(order);

        List<OrderItemResponse> responseItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {

            Long productId = entry.getKey();
            Integer qty = entry.getValue();

            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < qty) {
                throw new RuntimeException("Not enough stock: " + product.getName());
            }

            BigDecimal subtotal =
                    product.getPrice().multiply(BigDecimal.valueOf(qty));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(qty)
                    .unitPrice(product.getPrice())
                    .subtotal(subtotal)
                    .build();

            orderItemsRepo.save(orderItem);

            product.setStock(product.getStock() - qty);
            productRepo.save(product);

            total = total.add(subtotal);

            responseItems.add(
                    OrderItemResponse.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .quantity(qty)
                            .unitPrice(product.getPrice())
                            .subtotal(subtotal)
                            .build()
            );
        }

        order.setTotalAmount(total);
        orderRepo.save(order);

        session.removeAttribute(CART);

        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .phoneNumber(order.getPhoneNumber())
                .shippingAddress(order.getShippingAddress())
                .email(order.getEmail())
                .totalAmount(total)
                .status(order.getStatus())
                .items(responseItems)
                .createdAt(order.getCreatedAt())
                .build();
    }
}