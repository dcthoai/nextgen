package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IProductDTO;
import com.dct.nextgen.dto.product.ProductDTO;
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
import com.dct.nextgen.entity.Product;
import com.dct.nextgen.entity.ProductCarousel;
import com.dct.nextgen.entity.ProductIntro;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.ProductCarouselRepository;
import com.dct.nextgen.repositories.ProductIntroRepository;
import com.dct.nextgen.repositories.ProductPackCompareRepository;
import com.dct.nextgen.repositories.ProductPackDetailRepository;
import com.dct.nextgen.repositories.ProductPackRepository;
import com.dct.nextgen.repositories.ProductRepository;
import com.dct.nextgen.service.ProductService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String ENTITY_NAME = "ProductServiceImpl";
    private final FileUtils fileUtils;
    private final ProductRepository productRepository;
    private final ProductPackRepository productPackRepository;
    private final ProductIntroRepository productIntroRepository;
    private final ProductCarouselRepository productCarouselRepository;
    private final ProductPackDetailRepository productPackDetailRepository;
    private final ProductPackCompareRepository productPackCompareRepository;

    public ProductServiceImpl(FileUtils fileUtils,
                              ProductRepository productRepository,
                              ProductPackRepository productPackRepository,
                              ProductIntroRepository productIntroRepository,
                              ProductCarouselRepository productCarouselRepository,
                              ProductPackCompareRepository productPackCompareRepository,
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
        if (request.getPageable().isPaged()) {
            Page<IProductDTO> productsWithPaged = productRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(productsWithPaged.getTotalElements()).ok(productsWithPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(productRepository.findAllNonPaging());
    }

    @Override
    public BaseResponseDTO getProductInfo(Integer productId) {
        Optional<IProductDTO> productOptional = productRepository.findProductById(productId);

        if (productOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        ProductDTO product = new ProductDTO();
        BeanUtils.copyProperties(productOptional.get(), product);
        product.setCarouselUrls(productCarouselRepository.findAllCarouselImages(productId));
        product.setIntros(productIntroRepository.findAllIntros(productId));

        return BaseResponseDTO.builder().ok(product);
    }

    @Override
    public BaseResponseDTO getProductDetail(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Product product = productOptional.get();
        ProductDTO productDetail = new ProductDTO();
        BeanUtils.copyProperties(product, productDetail);
        Common.setAuditingInfo(product, productDetail);
        productDetail.setProductIntros(productIntroRepository.findAllIntrosByProductId(productId));
        productDetail.setCarousels(productCarouselRepository.findByProductId(productId));

        return BaseResponseDTO.builder().ok(productDetail);
    }

    @Override
    public BaseResponseDTO createNewProduct(CreateProductRequestDTO request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        List<String> carouselImageUrls = fileUtils.autoCompressImageAndSave(request.getCarouselFiles());

        if (Objects.isNull(carouselImageUrls) || carouselImageUrls.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.CANNOT_SAVE_IMAGE);
        }

        Set<ProductCarousel> productCarousels = new HashSet<>();
        List<ProductIntro> productIntros = new ArrayList<>();
        int position = 1;

        for (String imageUrl : carouselImageUrls) {
            ProductCarousel productCarousel = new ProductCarousel();
            productCarousel.setProduct(product);
            productCarousel.setUrl(imageUrl);
            productCarousels.add(productCarousel);
        }

        for (String intro : request.getIntros()) {
            ProductIntro productIntro = new ProductIntro();
            productIntro.setProduct(product);
            productIntro.setContent(intro);
            productIntro.setPosition(position++);
            productIntros.add(productIntro);
        }

        product.setProductIntros(productIntros);
        product.setProductCarousels(productCarousels);
        productRepository.save(product);

        return BaseResponseDTO.builder().ok(product);
    }

    @Override
    public BaseResponseDTO updateProduct(UpdateProductRequestDTO request) {
        Optional<Product> productOptional = productRepository.findById(request.getId());

        if (productOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Product product = productOptional.get();
        BeanUtils.copyProperties(request, product);

        return BaseResponseDTO.builder().ok(product);
    }

    @Override
    public BaseResponseDTO deleteProduct(Integer productId) {
        if (Objects.isNull(productId) || productId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        Set<String> oldCarouselToDelete = productCarouselRepository.findAllCarouselImages(productId);
        productRepository.deleteById(productId);
        fileUtils.delete(oldCarouselToDelete);
        return BaseResponseDTO.builder().ok();
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
