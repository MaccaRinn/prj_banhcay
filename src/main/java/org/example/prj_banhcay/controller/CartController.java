package org.example.prj_banhcay.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prj_banhcay.DTO.request.AddToCartRequest;
import org.example.prj_banhcay.DTO.request.UpdateCartRequest;
import org.example.prj_banhcay.DTO.response.CartResponse;
import org.example.prj_banhcay.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private  CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @Valid @RequestBody AddToCartRequest request,
            HttpSession session) {

        cartService.addToCart(request, session);

        return ResponseEntity.ok("Product added to cart");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(
            @Valid @RequestBody UpdateCartRequest request,
            HttpSession session) {

        cartService.updateCart(request, session);

        return ResponseEntity.ok("Cart updated");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> remove(
            @PathVariable Long productId,
            HttpSession session) {

        cartService.remove(productId, session);

        return ResponseEntity.ok("Product removed");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clear(
            HttpSession session) {

        cartService.clear(session);

        return ResponseEntity.ok("Cart cleared");
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(
            HttpSession session) {

        return ResponseEntity.ok(cartService.getCart(session));
    }

}