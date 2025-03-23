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
@RequestMapping("/api/about")
public class AboutResource {

    private static final Logger log = LoggerFactory.getLogger(AboutResource.class);

    @GetMapping("/stories")
    public BaseResponseDTO getAllStoriesWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/stories/{storyID}")
    public BaseResponseDTO getStoryDetail(@PathVariable Integer storyID) {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/stories")
    public BaseResponseDTO updateStory() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/mottos")
    public BaseResponseDTO getAllMottosWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/mottos/{mottoID}")
    public BaseResponseDTO getMottoDetail(@PathVariable Integer mottoID) {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping("/mottos")
    public BaseResponseDTO createNewMotto() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/mottos")
    public BaseResponseDTO updateMotto() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/mottos/{mottoID}")
    public BaseResponseDTO deleteMotto(@PathVariable Integer mottoID) {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/contact-form")
    public BaseResponseDTO getFormContactInfo() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/contact-form")
    public BaseResponseDTO updateContactFormInfo() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/partners")
    public BaseResponseDTO getAllPartnersWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/partners/{partnerID}")
    public BaseResponseDTO getPartnerDetail(@PathVariable Integer partnerID) {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping("/partners")
    public BaseResponseDTO createNewPartner() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/partners")
    public BaseResponseDTO updatePartner() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/partners/{partnerID}")
    public BaseResponseDTO deletePartner(@PathVariable Integer partnerID) {

        return BaseResponseDTO.builder().ok();
    }
}
