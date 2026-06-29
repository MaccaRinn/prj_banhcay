package org.example.prj_banhcay.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.prj_banhcay.DTO.request.CreateOrderRequest;
import org.example.prj_banhcay.DTO.response.OrderResponse;
import org.example.prj_banhcay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            HttpSession session) {

        return ResponseEntity.ok(
                orderService.createOrder(request, session)
        );
    }
}
