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

        return new BaseResponseDTO();
    }

    @PostMapping("/categories")
    public BaseResponseDTO createNewCategory() {

        return new BaseResponseDTO();
    }

    @PutMapping("/categories")
    public BaseResponseDTO updateCategory() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/categories/{categoryID}")
    public BaseResponseDTO deleteCategory(@PathVariable Integer categoryID) {

        return new BaseResponseDTO();
    }

    @GetMapping
    public BaseResponseDTO getAllProductsWithPaging() {

        return new BaseResponseDTO();
    }

    @GetMapping("/{productID}")
    public BaseResponseDTO getProductDetail(@PathVariable Integer productID) {

        return new BaseResponseDTO();
    }

    @PostMapping
    public BaseResponseDTO createNewProduct() {

        return new BaseResponseDTO();
    }

    @PutMapping
    public BaseResponseDTO updateProduct() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/{productID}")
    public BaseResponseDTO deleteProduct(@PathVariable Integer productID) {

        return new BaseResponseDTO();
    }
}
