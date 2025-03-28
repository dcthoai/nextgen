package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateBannerRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.service.HomeService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class HomeResource {

    private final HomeService homeService;
    private static final String ENTITY_NAME = "HomeResource";

    public HomeResource(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/p/banners")
    public BaseResponseDTO getAllBannersWithPaging(@RequestBody BaseRequestDTO requestDTO) {
        return homeService.getBannersWithPaging(requestDTO);
    }

    @GetMapping("/banners/{bannerId}")
    @CheckAuthorize(authorities = RoleConstants.Home.VIEW)
    public BaseResponseDTO getBannerDetail(@PathVariable Integer bannerId) {
        return homeService.getBannerDetail(bannerId);
    }

    @PutMapping("/banners")
    @CheckAuthorize(authorities = RoleConstants.Home.UPDATE)
    public BaseResponseDTO updateBanner(@Valid @ModelAttribute UpdateBannerRequestDTO requestDTO) {
        return homeService.updateBanner(requestDTO);
    }

    @GetMapping("/p/videos")
    public BaseResponseDTO getVideoHome() {
        return homeService.getVideoHome();
    }

    @PutMapping("/videos")
    @CheckAuthorize(authorities = RoleConstants.Home.UPDATE)
    public BaseResponseDTO updateVideoHome(@RequestParam MultipartFile video) {
        if (FileUtils.invalidUploadFile(video)) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        return homeService.updateVideoHome(video);
    }
}
