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

        return new BaseResponseDTO();
    }

    @GetMapping("/stories/{storyID}")
    public BaseResponseDTO getStoryDetail(@PathVariable Integer storyID) {

        return new BaseResponseDTO();
    }

    @PutMapping("/stories")
    public BaseResponseDTO updateStory() {

        return new BaseResponseDTO();
    }

    @GetMapping("/mottos")
    public BaseResponseDTO getAllMottosWithPaging() {

        return new BaseResponseDTO();
    }

    @GetMapping("/mottos/{mottoID}")
    public BaseResponseDTO getMottoDetail(@PathVariable Integer mottoID) {

        return new BaseResponseDTO();
    }

    @PostMapping("/mottos")
    public BaseResponseDTO createNewMotto() {

        return new BaseResponseDTO();
    }

    @PutMapping("/mottos")
    public BaseResponseDTO updateMotto() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/mottos/{mottoID}")
    public BaseResponseDTO deleteMotto(@PathVariable Integer mottoID) {

        return new BaseResponseDTO();
    }

    @GetMapping("/contact-form")
    public BaseResponseDTO getFormContactInfo() {

        return new BaseResponseDTO();
    }

    @PutMapping("/contact-form")
    public BaseResponseDTO updateContactFormInfo() {

        return new BaseResponseDTO();
    }

    @GetMapping("/partners")
    public BaseResponseDTO getAllPartnersWithPaging() {

        return new BaseResponseDTO();
    }

    @GetMapping("/partners/{partnerID}")
    public BaseResponseDTO getPartnerDetail(@PathVariable Integer partnerID) {

        return new BaseResponseDTO();
    }

    @PostMapping("/partners")
    public BaseResponseDTO createNewPartner() {

        return new BaseResponseDTO();
    }

    @PutMapping("/partners")
    public BaseResponseDTO updatePartner() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/partners/{partnerID}")
    public BaseResponseDTO deletePartner(@PathVariable Integer partnerID) {

        return new BaseResponseDTO();
    }
}
