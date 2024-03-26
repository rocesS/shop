package com.rocess.shop.controllers;

import com.rocess.shop.models.Product;
import com.rocess.shop.models.ProductDto;
import com.rocess.shop.services.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;

    @GetMapping({"","/"})
    public String showProductList (Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("products",products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {

        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);

        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {

        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto","imageFile","The image file is required"));
        }

        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        return "redirect:/products";
    }









}
