package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeResource {

    @GetMapping("/banners")
    public BaseResponseDTO getAllBannersWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/banners/{bannerID}")
    public BaseResponseDTO getBannerDetail(@PathVariable Integer bannerID) {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/banners")
    public BaseResponseDTO updateBanner() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/videos")
    public BaseResponseDTO getVideoHome() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/videos")
    public BaseResponseDTO updateVideoHome() {

        return BaseResponseDTO.builder().ok();
    }
}
