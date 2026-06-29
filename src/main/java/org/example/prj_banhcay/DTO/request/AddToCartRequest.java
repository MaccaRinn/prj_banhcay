package org.example.prj_banhcay.DTO.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    private long productId;
    private int quantity;
}
