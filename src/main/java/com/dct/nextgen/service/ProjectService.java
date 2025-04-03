package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateProjectRequestDTO;
import com.dct.nextgen.dto.request.UpdateProjectRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface ProjectService {

    BaseResponseDTO getAllProjectsWithPaging(BaseRequestDTO request);
    BaseResponseDTO getAllProjectsByCategoryWithPaging(BaseRequestDTO request, Integer categoryId);
    BaseResponseDTO getProjectInfo(Integer projectId);
    BaseResponseDTO getProjectDetail(Integer projectId);
    BaseResponseDTO createNewProject(CreateProjectRequestDTO request);
    BaseResponseDTO updateProject(UpdateProjectRequestDTO request);
    BaseResponseDTO deleteProject(Integer projectId);
}
