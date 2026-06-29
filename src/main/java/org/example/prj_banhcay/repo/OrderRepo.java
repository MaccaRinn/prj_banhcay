package org.example.prj_banhcay.repo;

import org.example.prj_banhcay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {


}
