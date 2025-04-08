package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.FileUtils;
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
import com.dct.nextgen.repositories.ProductCarouselRepository;
import com.dct.nextgen.repositories.ProductIntroRepository;
import com.dct.nextgen.repositories.ProductPackCompareRepository;
import com.dct.nextgen.repositories.ProductPackDetailRepository;
import com.dct.nextgen.repositories.ProductPackRepository;
import com.dct.nextgen.repositories.ProductRepository;
import com.dct.nextgen.service.ProductService;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String ENTITY_NAME = "ProductServiceImpl";
    private final FileUtils fileUtils;
    private final ProductRepository productRepository;
    private final ProductPackRepository productPackRepository;
    private final ProductCarouselRepository productCarouselRepository;
    private final ProductPackCompareRepository productPackCompareRepository;
    private final ProductIntroRepository productIntroRepository;
    private final ProductPackDetailRepository productPackDetailRepository;

    public ProductServiceImpl(FileUtils fileUtils,
                              ProductRepository productRepository,
                              ProductPackRepository productPackRepository,
                              ProductCarouselRepository productCarouselRepository,
                              ProductPackCompareRepository productPackCompareRepository,
                              ProductIntroRepository productIntroRepository,
                              ProductPackDetailRepository productPackDetailRepository) {
        this.fileUtils = fileUtils;
        this.productRepository = productRepository;
        this.productPackRepository = productPackRepository;
        this.productCarouselRepository = productCarouselRepository;
        this.productPackCompareRepository = productPackCompareRepository;
        this.productIntroRepository = productIntroRepository;
        this.productPackDetailRepository = productPackDetailRepository;
    }

    @Override
    public BaseResponseDTO getAllProductWithPaging(BaseRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductInfo(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductDetail(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO createNewProduct(CreateProductRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO updateProduct(UpdateProductRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO deleteProduct(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO getAllProductCarousel(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO createProductCarousel(CreateProductCarouselRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO updateProductCarousel(UpdateProductCarouselRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO getAllProductPack(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductPackDetail(Integer productPackId) {
        return null;
    }

    @Override
    public BaseResponseDTO createProductPack(CreateProductPackRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO updateProductPack(UpdateProductPackRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductPackCompareDetail(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO createProductPackCompare(CreateProductPackCompareRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO updateProductPackCompare(UpdateProductPackCompareRequestDTO request) {
        return null;
    }
}
