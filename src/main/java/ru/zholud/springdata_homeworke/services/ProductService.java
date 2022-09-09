package ru.zholud.springdata_homeworke.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zholud.springdata_homeworke.entities.Product;
import ru.zholud.springdata_homeworke.exeptions.ResourceNotFoundException;
import ru.zholud.springdata_homeworke.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changeScore(Long studentId, Integer delta) {
        Product product = productRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Unable to change student's score. Student not found, id: " + studentId));
        product.setPrice(product.getPrice() + delta);
    }

    public List<Product> findByPriceBetween(Integer min, Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }
}
