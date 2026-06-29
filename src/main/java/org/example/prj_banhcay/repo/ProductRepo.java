package org.example.prj_banhcay.repo;

import org.example.prj_banhcay.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {

    // list all product with active true
    List<Product> findByActiveTrue();
}
