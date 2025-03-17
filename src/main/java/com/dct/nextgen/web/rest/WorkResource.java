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
@RequestMapping("/api/works")
public class WorkResource {

    private static final Logger log = LoggerFactory.getLogger(WorkResource.class);

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

    @GetMapping("/projects")
    public BaseResponseDTO getAllProjectsWithPaging() {

        return new BaseResponseDTO();
    }

    @GetMapping("/projects/{projectID}")
    public BaseResponseDTO getProjectDetail(@PathVariable Integer projectID) {

        return new BaseResponseDTO();
    }

    @PostMapping("/projects")
    public BaseResponseDTO createNewProject() {

        return new BaseResponseDTO();
    }

    @PutMapping
    public BaseResponseDTO updateProject() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/{projectID}")
    public BaseResponseDTO deleteProject(@PathVariable Integer projectID) {

        return new BaseResponseDTO();
    }
}
