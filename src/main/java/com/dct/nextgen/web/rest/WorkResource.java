package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateCategoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WorkResource {

    private final CategoryService categoryService;

    public WorkResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/p/categories")
    public BaseResponseDTO getAllCategoriesWithPaging(BaseRequestDTO requestDTO) {
        return categoryService.getAllCategoriesWithPaging(requestDTO);
    }

    @GetMapping("/categories/{categoryId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Category.VIEW)
    public BaseResponseDTO getCategoryDetail(@PathVariable Integer categoryId) {
        return categoryService.getCategoryDetail(categoryId);
    }

    @PostMapping("/categories")
    @CheckAuthorize(authorities = RoleConstants.Works.Category.CREATE)
    public BaseResponseDTO createNewCategory(@Valid @RequestBody CreateOrUpdateCategoryRequestDTO requestDTO) {
        return categoryService.createNewCategory(requestDTO);
    }

    @PutMapping("/categories")
    @CheckAuthorize(authorities = RoleConstants.Works.Category.UPDATE)
    public BaseResponseDTO updateCategory(@Valid @RequestBody CreateOrUpdateCategoryRequestDTO requestDTO) {
        return categoryService.updateCategory(requestDTO);
    }

    @DeleteMapping("/categories/{categoryId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Category.DELETE)
    public BaseResponseDTO deleteCategory(@PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/p/projects")
    public BaseResponseDTO getAllProjectsWithPaging(BaseRequestDTO requestDTO) {
        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/p/projects/{categoryId}")
    public BaseResponseDTO getProjectsWithPagingByCategory(BaseRequestDTO request, @PathVariable Integer categoryId) {
        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/projects/{projectId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.VIEW)
    public BaseResponseDTO getProjectDetail(@PathVariable Integer projectId) {
        return BaseResponseDTO.builder().ok();
    }

    @PostMapping("/projects")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.CREATE)
    public BaseResponseDTO createNewProject() {
        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    @CheckAuthorize(authorities = RoleConstants.Works.Project.UPDATE)
    public BaseResponseDTO updateProject() {
        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/{projectId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.DELETE)
    public BaseResponseDTO deleteProject(@PathVariable Integer projectId) {
        return BaseResponseDTO.builder().ok();
    }
}
