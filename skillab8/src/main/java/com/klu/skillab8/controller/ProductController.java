package com.klu.skillab8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.skillab8.entity.Product;
import com.klu.skillab8.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    // a. /products/category/{category}
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }

    // b. /products/filter?min=&max=
    @GetMapping("/filter")
    public List<Product> filterByPrice(@RequestParam double min,
                                       @RequestParam double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    // c. /products/sorted
    @GetMapping("/sorted")
    public List<Product> getSortedProducts() {
        return productRepository.sortProductsByPrice();
    }

    // d. /products/expensive/{price}
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return productRepository.findExpensiveProducts(price);
    }
}