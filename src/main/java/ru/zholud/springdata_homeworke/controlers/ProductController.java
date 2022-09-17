package ru.zholud.springdata_homeworke.controlers;


import ru.zholud.springdata_homeworke.exeptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zholud.springdata_homeworke.entities.Product;
import ru.zholud.springdata_homeworke.services.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }

    @GetMapping("/products/score_between")
    public List<Product> findStudentsByScoreBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max) {
        return productService.findByPriceBetween(min, max);
    }
}
