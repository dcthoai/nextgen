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

        return new BaseResponseDTO();
    }

    @GetMapping("/banners/{bannerID}")
    public BaseResponseDTO getBannerDetail(@PathVariable Integer bannerID) {

        return new BaseResponseDTO();
    }

    @PutMapping("/banners")
    public BaseResponseDTO updateBanner() {

        return new BaseResponseDTO();
    }

    @GetMapping("/videos")
    public BaseResponseDTO getVideoHome() {

        return new BaseResponseDTO();
    }

    @PutMapping("/videos")
    public BaseResponseDTO updateVideoHome() {

        return new BaseResponseDTO();
    }
}
