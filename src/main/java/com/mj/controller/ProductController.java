package com.mj.controller;

import com.mj.entity.Product;
import com.mj.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public List<Product> getProducts() throws InterruptedException {
        Thread.sleep(1000);
        log.info("Thread info {} ", Thread.currentThread());
        return productRepository.findAll();
    }

}