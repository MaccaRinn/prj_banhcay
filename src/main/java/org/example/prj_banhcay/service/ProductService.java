package org.example.prj_banhcay.service;

import org.example.prj_banhcay.model.Product;
import org.example.prj_banhcay.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {
    
    private ProductRepo productRepo;
    
    public Optional<Product> findProductById(long id){
        return productRepo.findById(id);
    }
    
}
