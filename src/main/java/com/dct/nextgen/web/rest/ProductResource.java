package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @GetMapping("/categories")
    public BaseResponseDTO getAllCategoriesWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping("/categories")
    public BaseResponseDTO createNewCategory() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/categories")
    public BaseResponseDTO updateCategory() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/categories/{categoryID}")
    public BaseResponseDTO deleteCategory(@PathVariable Integer categoryID) {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping
    public BaseResponseDTO getAllProductsWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/{productID}")
    public BaseResponseDTO getProductDetail(@PathVariable Integer productID) {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping
    public BaseResponseDTO createNewProduct() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    public BaseResponseDTO updateProduct() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/{productID}")
    public BaseResponseDTO deleteProduct(@PathVariable Integer productID) {

        return BaseResponseDTO.builder().ok();
    }
}
