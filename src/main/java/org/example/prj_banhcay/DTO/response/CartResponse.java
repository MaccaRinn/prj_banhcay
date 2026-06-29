package org.example.prj_banhcay.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private List<CartItemResponse> items;

    private BigDecimal totalAmount;

}
