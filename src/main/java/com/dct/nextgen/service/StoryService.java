package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateStoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface StoryService {

    BaseResponseDTO getAllStoriesWithPaging(BaseRequestDTO request);
    BaseResponseDTO getStoryDetail(String position);
    BaseResponseDTO getStory(String position);
    BaseResponseDTO updateStory(UpdateStoryRequestDTO request);
}
