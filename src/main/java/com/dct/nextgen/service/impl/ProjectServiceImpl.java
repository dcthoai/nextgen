package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.DateUtils;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.DatetimeConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IProjectDTO;
import com.dct.nextgen.dto.mapping.IProjectDetailDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateProjectRequestDTO;
import com.dct.nextgen.dto.request.UpdateProjectRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.dto.work.ProjectDTO;
import com.dct.nextgen.entity.CategoryProject;
import com.dct.nextgen.entity.Project;
import com.dct.nextgen.entity.ProjectImage;
import com.dct.nextgen.exception.BaseBadRequestAlertException;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.CategoryProjectRepository;
import com.dct.nextgen.repositories.CategoryRepository;
import com.dct.nextgen.repositories.ProjectImageRepository;
import com.dct.nextgen.repositories.ProjectRepository;
import com.dct.nextgen.service.ProjectService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final String ENTITY_NAME = "ProjectServiceImpl";
    private final FileUtils fileUtils;
    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryProjectRepository categoryProjectRepository;

    public ProjectServiceImpl(FileUtils fileUtils,
                              ProjectRepository projectRepository,
                              ProjectImageRepository projectImageRepository,
                              CategoryRepository categoryRepository,
                              CategoryProjectRepository categoryProjectRepository) {
        this.fileUtils = fileUtils;
        this.projectRepository = projectRepository;
        this.projectImageRepository = projectImageRepository;
        this.categoryRepository = categoryRepository;
        this.categoryProjectRepository = categoryProjectRepository;
    }

    @Override
    public BaseResponseDTO getAllProjectsWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IProjectDTO> projectsPaged = projectRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(projectsPaged.getTotalElements()).ok(projectsPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(projectRepository.findAllNonPaging());
    }

    @Override
    public BaseResponseDTO getAllProjectsByCategoryWithPaging(BaseRequestDTO request, Integer categoryId) {
        if (request.getPageable().isPaged()) {
            Page<IProjectDTO> projectsPaged = projectRepository.findAllByCategoryWithPaging(
                categoryId,
                request.getPageable()
            );
            return BaseResponseDTO.builder().total(projectsPaged.getTotalElements()).ok(projectsPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(projectRepository.findAllByCategoryNonPaging(categoryId));
    }

    @Override
    public BaseResponseDTO getProjectInfo(Integer projectId) {
        Optional<IProjectDetailDTO> projectOptional = projectRepository.findProjectById(projectId);

        if (projectOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        IProjectDetailDTO project = projectOptional.get();
        ProjectDTO projectDetail = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDetail, "finishedDate");

        String finishedDateStr = DateUtils
            .ofLocalDateTime(project.getFinishedDate())
            .toString(DatetimeConstants.Formatter.DD_MM_YYYY_SLASH);

        projectDetail.setFinishedDate(finishedDateStr);
        projectDetail.setCategories(categoryRepository.findAllByProjectId(projectId));
        projectDetail.setImagesUrls(projectImageRepository.findAllImageUrlByProjectId(projectId));

        return BaseResponseDTO.builder().ok(projectDetail);
    }

    @Override
    public BaseResponseDTO getProjectDetail(Integer projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Project project = projectOptional.get();
        ProjectDTO projectDetail = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDetail, "finishedDate");
        Common.setAuditingInfo(project, projectDetail);

        String finishedDateStr = DateUtils
            .ofLocalDateTime(project.getFinishedDate())
            .toString(DatetimeConstants.Formatter.DD_MM_YYYY_SLASH);

        projectDetail.setFinishedDate(finishedDateStr);
        projectDetail.setCategories(categoryRepository.findAllByProjectId(projectId));
        projectDetail.setProjectImages(projectImageRepository.findAllByProjectId(projectId));

        return BaseResponseDTO.builder().ok(projectDetail);
    }

    @Override
    @Transactional
    public BaseResponseDTO createNewProject(CreateProjectRequestDTO request) {
        Project project = new Project();
        BeanUtils.copyProperties(request, project, "finishedDate");
        String thumbnailRectUrl = fileUtils.autoCompressImageAndSave(request.getThumbnailRectFile());
        String thumbnailSquareUrl = fileUtils.autoCompressImageAndSave(request.getThumbnailSquareFile());
        List<String> projectImageUrls = fileUtils.autoCompressImageAndSave(request.getProjectImageFiles());
        List<ProjectImage> projectImages = new ArrayList<>();
        int position = 1;

        if (!StringUtils.hasText(thumbnailRectUrl) || !StringUtils.hasText(thumbnailSquareUrl)) {
            throw new BaseBadRequestAlertException(ENTITY_NAME, ExceptionConstants.CANNOT_SAVE_IMAGE);
        }

        for (String image : projectImageUrls) {
            ProjectImage projectImage = new ProjectImage();
            projectImage.setProject(project);
            projectImage.setUrl(image);
            projectImage.setPosition(position++);
            projectImages.add(projectImage);
        }

        project.setImages(projectImages);
        project.setThumbnailRect(thumbnailRectUrl);
        project.setThumbnailSquare(thumbnailSquareUrl);

        Instant finishedDate = DateUtils
            .ofInstant(
                request.getFinishedDate(),
                DatetimeConstants.Formatter.DD_MM_YYYY_HH_MM_SS_DASH,
                DatetimeConstants.ZoneID.ASIA_HO_CHI_MINH
            )
            .getInstance();

        project.setFinishedDate(finishedDate);
        projectRepository.save(project);

        List<Integer> categoryIds = categoryRepository.findAllCategoryIdsIn(request.getCategoryIds());

        if (Objects.isNull(categoryIds) || categoryIds.size() != request.getCategoryIds().size()) {
            throw new BaseBadRequestAlertException(ENTITY_NAME, ExceptionConstants.CATEGORY_IDS_INVALID);
        }

        saveProjectCategories(project, categoryIds);
        return BaseResponseDTO.builder().ok();
    }

    @Async
    @Transactional
    protected void saveProjectCategories(Project project, List<Integer> categoryIds) {
        List<CategoryProject> categoryProjects = new ArrayList<>();

        for (Integer categoryId : categoryIds) {
            CategoryProject categoryProject = new CategoryProject();
            categoryProject.setProjectId(project.getId());
            categoryProject.setCategoryId(categoryId);
            categoryProjects.add(categoryProject);
        }

        categoryProjectRepository.saveAll(categoryProjects);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateProject(UpdateProjectRequestDTO request) {
        return null;
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteProject(Integer projectId) {
        if (Objects.isNull(projectId) || projectId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        projectRepository.deleteById(projectId);

        return BaseResponseDTO.builder().ok();
    }
}
