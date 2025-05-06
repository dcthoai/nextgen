package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.ICategoryDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateCategoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.dto.work.CategoryDTO;
import com.dct.nextgen.entity.Category;
import com.dct.nextgen.entity.CategoryProject;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.CategoryProjectRepository;
import com.dct.nextgen.repositories.CategoryRepository;
import com.dct.nextgen.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String ENTITY_NAME = "CategoryServiceImpl";
    private final CategoryRepository categoryRepository;
    private final CategoryProjectRepository categoryProjectRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryProjectRepository categoryProjectRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryProjectRepository = categoryProjectRepository;
    }

    @Override
    public BaseResponseDTO getAllCategoriesWithPaging(BaseRequestDTO request) {
        Page<ICategoryDTO> categoriesWithPaged = categoryRepository.findAllWithPaging(request.getPageable());
        return BaseResponseDTO.builder()
            .total(categoriesWithPaged.getTotalElements())
            .ok(categoriesWithPaged.getContent());
    }

    @Override
    public BaseResponseDTO getCategoryDetail(Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Category category = categoryOptional.get();
        CategoryDTO categoryDetail = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDetail);
        Common.setAuditingInfo(category, categoryDetail);
        categoryDetail.setQuantity(categoryRepository.countCategoryProjectQuantity(categoryId));

        return BaseResponseDTO.builder().ok(categoryDetail);
    }

    @Override
    @Transactional
    public BaseResponseDTO createNewCategory(CreateOrUpdateCategoryRequestDTO request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category, "id");
        return BaseResponseDTO.builder().ok(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public BaseResponseDTO updateCategory(CreateOrUpdateCategoryRequestDTO request) {
        Optional<Category> categoryOptional = categoryRepository.findById(request.getId());

        if (categoryOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Category category = categoryOptional.get();
        BeanUtils.copyProperties(request, category);

        return BaseResponseDTO.builder().ok(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteCategory(Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        List<CategoryProject> categoryProjects = categoryProjectRepository.findAllByCategoryId(categoryId);
        categoryProjectRepository.deleteAll(categoryProjects);
        categoryRepository.delete(categoryOptional.get());

        return BaseResponseDTO.builder().ok();
    }
}
