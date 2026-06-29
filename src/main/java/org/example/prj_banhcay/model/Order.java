package org.example.prj_banhcay.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.prj_banhcay.Enum.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer information
    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private String email;

    // Total order amount
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    // Order status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column
    private String note;

    // Relationship with OrderItem
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (status == null) {
            status = OrderStatus.PENDING;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
