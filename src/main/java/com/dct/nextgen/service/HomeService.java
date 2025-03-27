package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateBannerRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface HomeService {

    BaseResponseDTO getBannersWithPaging(BaseRequestDTO request);
    BaseResponseDTO getBannerDetail(Integer bannerID);
    BaseResponseDTO updateBanner(UpdateBannerRequestDTO request);
    BaseResponseDTO getVideoHome();
    BaseResponseDTO updateVideoHome(MultipartFile video);
}
