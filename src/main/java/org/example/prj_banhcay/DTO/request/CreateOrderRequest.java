package org.example.prj_banhcay.DTO.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotBlank
    private String customerName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String shippingAddress;

    @Email
    @NotBlank
    private String email;

    private String note;
}
