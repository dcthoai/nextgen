package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IProductDTO;
import com.dct.nextgen.dto.product.ProductCarouselDTO;
import com.dct.nextgen.dto.product.ProductDTO;
import com.dct.nextgen.dto.product.ProductIntroDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateProductPackCompareRequestDTO;
import com.dct.nextgen.dto.request.CreateProductPackRequestDTO;
import com.dct.nextgen.dto.request.CreateProductRequestDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String ENTITY_NAME = "ProductServiceImpl";
    private final FileUtils fileUtils;
    private final ProductRepository productRepository;
    private final ProductIntroRepository productIntroRepository;
    private final ProductCarouselRepository productCarouselRepository;
    private final ProductPackRepository productPackRepository;
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
        Page<IProductDTO> productsWithPaged = productRepository.findAllWithPaging(request.getPageable());
        return BaseResponseDTO.builder().total(productsWithPaged.getTotalElements()).ok(productsWithPaged.getContent());
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
    @Transactional
    public BaseResponseDTO createNewProduct(CreateProductRequestDTO request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        List<String> carouselImageUrls = fileUtils.autoCompressImageAndSave(request.getCarouselFiles());

        if (Objects.isNull(carouselImageUrls) || carouselImageUrls.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.CANNOT_SAVE_IMAGE);
        }

        List<ProductCarousel> productCarousels = new ArrayList<>();
        List<ProductIntro> productIntros = new ArrayList<>();
        int position = 1;

        for (String intro : request.getIntros()) {
            ProductIntro productIntro = new ProductIntro();
            productIntro.setProduct(product);
            productIntro.setContent(intro);
            productIntro.setPosition(position++);
            productIntros.add(productIntro);
        }

        product.setProductIntros(productIntros);
        productRepository.save(product);

        for (String imageUrl : carouselImageUrls) {
            ProductCarousel productCarousel = new ProductCarousel();
            productCarousel.setProductId(product.getId());
            productCarousel.setUrl(imageUrl);
            productCarousels.add(productCarousel);
        }

        productCarouselRepository.saveAll(productCarousels);
        product.setProductCarousels(productCarousels);

        return BaseResponseDTO.builder().ok(product);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateProduct(UpdateProductRequestDTO request) {
        Optional<Product> productOptional = productRepository.findById(request.getId());

        if (productOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Product product = productOptional.get();
        BeanUtils.copyProperties(request, product);
        updateProductIntros(product, request.getIntros());
        productRepository.save(product);
        List<String> oldImageToDelete = updateProductCarousels(product, request.getCarousels());
        fileUtils.delete(oldImageToDelete);

        return BaseResponseDTO.builder().ok(product);
    }

    private void updateProductIntros(Product product, List<ProductIntroDTO> productIntroDTOS) {
        List<ProductIntro> updatedIntros = new ArrayList<>();
        Map<Integer, ProductIntro> positionMap = product.getProductIntros().stream()
            .collect(Collectors.toMap(ProductIntro::getId, Function.identity()));
        int position = 1;

        for (ProductIntroDTO productIntroDTO : productIntroDTOS) {
            ProductIntro existingIntro = positionMap.get(productIntroDTO.getId());

            if (Objects.nonNull(existingIntro)) {
                existingIntro.setContent(productIntroDTO.getContent());
                existingIntro.setPosition(position++);
                updatedIntros.add(existingIntro);
            } else {
                ProductIntro productIntro = new ProductIntro();
                productIntro.setProduct(product);
                productIntro.setContent(productIntroDTO.getContent());
                productIntro.setPosition(position++);
                updatedIntros.add(productIntro);
            }
        }

        product.getProductIntros().addAll(updatedIntros);
    }

    private List<String> updateProductCarousels(Product product, List<ProductCarouselDTO> carouselDTOs) {
        List<String> oldCarousels = product.getProductCarousels().stream().map(ProductCarousel::getUrl).toList();
        List<Integer> oldCarouselIds = product.getProductCarousels().stream().map(ProductCarousel::getId).toList();
        List<String> imageUrlsToDelete = new ArrayList<>(oldCarousels);
        List<ProductCarousel> updatedCarousels = new ArrayList<>();

        for (ProductCarouselDTO productCarouselDTO : carouselDTOs) {
            ProductCarousel productCarousel = new ProductCarousel();
            productCarousel.setProductId(product.getId());
            productCarousel.setUrl(productCarouselDTO.getUrl());
            String newUrl = fileUtils.autoCompressImageAndSave(productCarouselDTO.getImage());

            if (StringUtils.hasText(newUrl)) {
                productCarousel.setUrl(newUrl);
            } else {
                imageUrlsToDelete.remove(productCarouselDTO.getUrl());
            }

            updatedCarousels.add(productCarousel);
        }

        productCarouselRepository.deleteAllByIDs(oldCarouselIds);
        productCarouselRepository.saveAll(updatedCarousels);

        return imageUrlsToDelete;
    }

    @Override
    @Transactional
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
    public BaseResponseDTO getAllProductPack(Integer productId) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductPackDetail(Integer productPackId) {
        return null;
    }

    @Override
    @Transactional
    public BaseResponseDTO createProductPack(CreateProductPackRequestDTO request) {
        return null;
    }

    @Override
    @Transactional
    public BaseResponseDTO updateProductPack(UpdateProductPackRequestDTO request) {
        return null;
    }

    @Override
    public BaseResponseDTO getProductPackCompareDetail(Integer productId) {
        return null;
    }

    @Override
    @Transactional
    public BaseResponseDTO createProductPackCompare(CreateProductPackCompareRequestDTO request) {
        return null;
    }

    @Override
    @Transactional
    public BaseResponseDTO updateProductPackCompare(UpdateProductPackCompareRequestDTO request) {
        return null;
    }
}
