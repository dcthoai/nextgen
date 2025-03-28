package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.FileUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.company.BannerDTO;
import com.dct.nextgen.dto.mapping.IBannerDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateBannerRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Banner;
import com.dct.nextgen.exception.BaseBadRequestAlertException;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.BannerRepository;
import com.dct.nextgen.repositories.CompanyRepository;
import com.dct.nextgen.service.HomeService;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    private static final String ENTITY_NAME = "HomeServiceImpl";
    private final FileUtils fileUtils;
    private final BannerRepository bannerRepository;
    private final CompanyRepository companyRepository;

    public HomeServiceImpl(FileUtils fileUtils,
                           BannerRepository bannerRepository,
                           CompanyRepository companyRepository) {
        this.fileUtils = fileUtils;
        this.bannerRepository = bannerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public BaseResponseDTO getBannersWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IBannerDTO> bannersWithPaged = bannerRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(bannersWithPaged.getTotalElements()).ok(bannersWithPaged.getContent());
        }

        List<IBannerDTO> banners = bannerRepository.findAllNonPaging();
        return BaseResponseDTO.builder().total((long) banners.size()).ok(banners);
    }

    @Override
    public BaseResponseDTO getBannerDetail(Integer bannerId) {
        Optional<Banner> bannerOptional = bannerRepository.findById(bannerId);

        if (bannerOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Banner banner = bannerOptional.get();
        BannerDTO bannerDetail = new BannerDTO();
        BeanUtils.copyProperties(banner, bannerDetail);
        Common.setAuditingInfo(banner, bannerDetail);

        return BaseResponseDTO.builder().ok(bannerDetail);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateBanner(UpdateBannerRequestDTO request) {
        Optional<Banner> bannerOptional = bannerRepository.findBannerByPosition(request.getPosition());
        Banner banner = bannerOptional.orElseGet(Banner::new);
        String oldBannerImageUrl = banner.getImage();
        BeanUtils.copyProperties(request, banner);
        String bannerImageUrl = fileUtils.autoCompressImageAndSave(request.getImageFile());

        if (StringUtils.hasText(bannerImageUrl)) {
            banner.setImage(bannerImageUrl);

            if (StringUtils.hasText(oldBannerImageUrl)) {
                fileUtils.delete(oldBannerImageUrl);
            }
        }

        bannerRepository.save(banner);
        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO getVideoHome() {
        String videoUrl = companyRepository.getVideoIntro();
        return BaseResponseDTO.builder().ok(videoUrl);
    }

    @Override
    @Transactional
    public BaseResponseDTO updateVideoHome(MultipartFile video) {
        String videoUrl = fileUtils.save(video);

        if (!StringUtils.hasText(videoUrl)) {
            throw new BaseBadRequestAlertException(ENTITY_NAME, ExceptionConstants.CANNOT_SAVE_VIDEO);
        }

        String oldVideoUrl = companyRepository.getVideoIntro();
        companyRepository.updateVideoIntro(videoUrl);
        fileUtils.delete(oldVideoUrl);

        return BaseResponseDTO.builder().ok(videoUrl);
    }
}
