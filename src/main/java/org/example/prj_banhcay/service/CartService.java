package org.example.prj_banhcay.service;

import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import org.example.prj_banhcay.DTO.request.AddToCartRequest;
import org.example.prj_banhcay.DTO.request.UpdateCartRequest;
import org.example.prj_banhcay.DTO.response.CartItemResponse;
import org.example.prj_banhcay.DTO.response.CartResponse;
import org.example.prj_banhcay.model.Product;
import org.example.prj_banhcay.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private  ProductRepo productRepo;

    private static final String CART = "cart";

    @SuppressWarnings("unchecked")
    private Map<Long, Integer> getSessionCart(HttpSession session) {

        Map<Long, Integer> cart =
                (Map<Long, Integer>) session.getAttribute(CART);

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(CART, cart);
        }

        return cart;
    }

    public void addToCart(AddToCartRequest request,
                          HttpSession session) {

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        if (!Boolean.TRUE.equals(product.getActive())) {
            throw new RuntimeException("Product unavailable");
        }

        Map<Long, Integer> cart = getSessionCart(session);

        cart.merge(
                request.getProductId(),
                request.getQuantity(),
                Integer::sum
        );

        session.setAttribute(CART, cart);
    }

    public void updateCart(UpdateCartRequest request,
                           HttpSession session) {

        Map<Long, Integer> cart = getSessionCart(session);

        if (!cart.containsKey(request.getProductId())) {
            throw new RuntimeException("Product not in cart");
        }

        cart.put(request.getProductId(), request.getQuantity());
    }

    public void remove(Long productId,
                       HttpSession session) {

        Map<Long, Integer> cart = getSessionCart(session);

        cart.remove(productId);
    }

    public void clear(HttpSession session) {
        session.removeAttribute(CART);
    }

    public CartResponse getCart(HttpSession session) {

        Map<Long, Integer> cart = getSessionCart(session);

        List<CartItemResponse> items = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {

            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow();

            Integer quantity = entry.getValue();

            BigDecimal subtotal =
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(quantity));

            total = total.add(subtotal);

            items.add(
                    CartItemResponse.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .imageUrl(product.getImageUrl())
                            .price(product.getPrice())
                            .quantity(quantity)
                            .subtotal(subtotal)
                            .build()
            );
        }

        return CartResponse.builder()
                .items(items)
                .totalAmount(total)
                .build();
    }

}
