package org.example.prj_banhcay.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.prj_banhcay.DTO.request.CreateOrderRequest;
import org.example.prj_banhcay.DTO.response.OrderResponse;
import org.example.prj_banhcay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "https://www.banhcaynguvi.com", allowCredentials = "true")
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
