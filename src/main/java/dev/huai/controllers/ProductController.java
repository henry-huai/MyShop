package dev.huai.controllers;

import dev.huai.models.Product;
import dev.huai.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    @ResponseBody
    public boolean addNewProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
