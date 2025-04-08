package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateProductCarouselRequestDTO;
import com.dct.nextgen.dto.request.CreateProductPackCompareRequestDTO;
import com.dct.nextgen.dto.request.CreateProductPackRequestDTO;
import com.dct.nextgen.dto.request.CreateProductRequestDTO;
import com.dct.nextgen.dto.request.UpdateProductCarouselRequestDTO;
import com.dct.nextgen.dto.request.UpdateProductPackCompareRequestDTO;
import com.dct.nextgen.dto.request.UpdateProductPackRequestDTO;
import com.dct.nextgen.dto.request.UpdateProductRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/p/products")
    public BaseResponseDTO getAllProductsWithPaging(BaseRequestDTO requestDTO) {
        return productService.getAllProductWithPaging(requestDTO);
    }

    @GetMapping("/p/products/{productId}")
    public BaseResponseDTO getProductInfo(@PathVariable Integer productId) {
        return productService.getProductInfo(productId);
    }

    @GetMapping("/products/{productId}")
    @CheckAuthorize(authorities = RoleConstants.Product.VIEW)
    public BaseResponseDTO getProductDetail(@PathVariable Integer productId) {
        return productService.getProductDetail(productId);
    }

    @PostMapping("/products")
    @CheckAuthorize(authorities = RoleConstants.Product.CREATE)
    public BaseResponseDTO createNewProduct(@Valid @RequestBody CreateProductRequestDTO requestDTO) {
        return productService.createNewProduct(requestDTO);
    }

    @PutMapping("/products")
    @CheckAuthorize(authorities = RoleConstants.Product.UPDATE)
    public BaseResponseDTO updateProduct(@Valid @RequestBody UpdateProductRequestDTO requestDTO) {
        return productService.updateProduct(requestDTO);
    }

    @DeleteMapping("/products/{productId}")
    @CheckAuthorize(authorities = RoleConstants.Product.DELETE)
    public BaseResponseDTO deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/products/carousels/{productId}")
    @CheckAuthorize(authorities = RoleConstants.Product.VIEW)
    public BaseResponseDTO getAllProductCarousel(@PathVariable Integer productId) {
        return productService.getAllProductCarousel(productId);
    }

    @PostMapping("/products/carousels")
    @CheckAuthorize(authorities = RoleConstants.Product.CREATE)
    public BaseResponseDTO createProductCarousel(@Valid @ModelAttribute CreateProductCarouselRequestDTO requestDTO) {
        return productService.createProductCarousel(requestDTO);
    }

    @PutMapping("/products/carousels")
    @CheckAuthorize(authorities = RoleConstants.Product.UPDATE)
    public BaseResponseDTO updateProductCarousel(@Valid @ModelAttribute UpdateProductCarouselRequestDTO requestDTO) {
        return productService.updateProductCarousel(requestDTO);
    }

    @GetMapping("/products/packs/{productId}")
    @CheckAuthorize(authorities = RoleConstants.Product.VIEW)
    public BaseResponseDTO getAllProductPacks(@PathVariable Integer productId) {
        return productService.getAllProductPack(productId);
    }

    @GetMapping("/products/packs/view/{productPackId}")
    @CheckAuthorize(authorities = RoleConstants.Product.VIEW)
    public BaseResponseDTO getProductPackDetail(@PathVariable Integer productPackId) {
        return productService.getProductPackDetail(productPackId);
    }

    @PostMapping("/products/packs")
    @CheckAuthorize(authorities = RoleConstants.Product.CREATE)
    public BaseResponseDTO createProductPack(@Valid @ModelAttribute CreateProductPackRequestDTO productPackRequestDTO) {
        return productService.createProductPack(productPackRequestDTO);
    }

    @PutMapping("/products/packs")
    @CheckAuthorize(authorities = RoleConstants.Product.UPDATE)
    public BaseResponseDTO updateProductPack(@Valid @ModelAttribute UpdateProductPackRequestDTO productPackRequestDTO) {
        return productService.updateProductPack(productPackRequestDTO);
    }

    @GetMapping("/products/packs-compare/{productId}")
    @CheckAuthorize(authorities = RoleConstants.Product.VIEW)
    public BaseResponseDTO getProductPackCompareTable(@PathVariable Integer productId) {
        return productService.getProductPackCompareDetail(productId);
    }

    @PostMapping("/products/packs-compare")
    @CheckAuthorize(authorities = RoleConstants.Product.CREATE)
    public BaseResponseDTO createProductPackCompare(@Valid @RequestBody CreateProductPackCompareRequestDTO request) {
        return productService.createProductPackCompare(request);
    }

    @PutMapping("/products/packs-compare")
    @CheckAuthorize(authorities = RoleConstants.Product.UPDATE)
    public BaseResponseDTO updateProductPackCompare(@Valid @RequestBody UpdateProductPackCompareRequestDTO request) {
        return productService.updateProductPackCompare(request);
    }
}
