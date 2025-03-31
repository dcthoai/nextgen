package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateCategoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface CategoryService {

    BaseResponseDTO getAllCategoriesWithPaging(BaseRequestDTO request);
    BaseResponseDTO getCategoryDetail(Integer categoryId);
    BaseResponseDTO createNewCategory(CreateOrUpdateCategoryRequestDTO request);
    BaseResponseDTO updateCategory(CreateOrUpdateCategoryRequestDTO request);
    BaseResponseDTO deleteCategory(Integer categoryId);
}
