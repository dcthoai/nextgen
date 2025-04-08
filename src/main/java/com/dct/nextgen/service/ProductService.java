package com.dct.nextgen.service;

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

public interface ProductService {

    BaseResponseDTO getAllProductWithPaging(BaseRequestDTO request);
    BaseResponseDTO getProductInfo(Integer productId);
    BaseResponseDTO getProductDetail(Integer productId);
    BaseResponseDTO createNewProduct(CreateProductRequestDTO request);
    BaseResponseDTO updateProduct(UpdateProductRequestDTO request);
    BaseResponseDTO deleteProduct(Integer productId);
    BaseResponseDTO getAllProductCarousel(Integer productId);
    BaseResponseDTO createProductCarousel(CreateProductCarouselRequestDTO request);
    BaseResponseDTO updateProductCarousel(UpdateProductCarouselRequestDTO request);
    BaseResponseDTO getAllProductPack(Integer productId);
    BaseResponseDTO getProductPackDetail(Integer productPackId);
    BaseResponseDTO createProductPack(CreateProductPackRequestDTO request);
    BaseResponseDTO updateProductPack(UpdateProductPackRequestDTO request);
    BaseResponseDTO getProductPackCompareDetail(Integer productId);
    BaseResponseDTO createProductPackCompare(CreateProductPackCompareRequestDTO request);
    BaseResponseDTO updateProductPackCompare(UpdateProductPackCompareRequestDTO request);
}
