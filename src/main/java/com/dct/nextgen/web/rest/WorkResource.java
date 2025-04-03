package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdateCategoryRequestDTO;
import com.dct.nextgen.dto.request.CreateProjectRequestDTO;
import com.dct.nextgen.dto.request.UpdateProjectRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.CategoryService;
import com.dct.nextgen.service.ProjectService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class WorkResource {

    private final CategoryService categoryService;
    private final ProjectService projectService;

    public WorkResource(CategoryService categoryService, ProjectService projectService) {
        this.categoryService = categoryService;
        this.projectService = projectService;
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
    public BaseResponseDTO getAllProjectsWithPaging(@RequestBody BaseRequestDTO requestDTO,
                                                    @RequestParam(value = "category", required = false) Integer id) {
        if (Objects.nonNull(id) && id > 0) {
            return projectService.getAllProjectsByCategoryWithPaging(requestDTO, id);
        }

        return projectService.getAllProjectsWithPaging(requestDTO);
    }

    @GetMapping("/p/projects/{projectId}")
    public BaseResponseDTO getProjectInfo(@PathVariable Integer projectId) {
        return projectService.getProjectInfo(projectId);
    }

    @GetMapping("/projects/{projectId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.VIEW)
    public BaseResponseDTO getProjectDetail(@PathVariable Integer projectId) {
        return projectService.getProjectDetail(projectId);
    }

    @PostMapping("/projects")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.CREATE)
    public BaseResponseDTO createNewProject(@Valid @ModelAttribute CreateProjectRequestDTO requestDTO) {
        return projectService.createNewProject(requestDTO);
    }

    @PutMapping("/projects")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.UPDATE)
    public BaseResponseDTO updateProject(@Valid UpdateProjectRequestDTO requestDTO) {
        return projectService.updateProject(requestDTO);
    }

    @DeleteMapping("/projects/{projectId}")
    @CheckAuthorize(authorities = RoleConstants.Works.Project.DELETE)
    public BaseResponseDTO deleteProject(@PathVariable Integer projectId) {
        return projectService.deleteProject(projectId);
    }
}
