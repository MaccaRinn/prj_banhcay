package org.example.prj_banhcay.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private Long productId;

    private String productName;

    private String imageUrl;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal subtotal;
}
