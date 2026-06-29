package org.example.prj_banhcay.repo;

import org.example.prj_banhcay.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItem, Long> {

}
