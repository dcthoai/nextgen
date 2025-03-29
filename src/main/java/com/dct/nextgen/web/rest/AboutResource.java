package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.UpdateStoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.StoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AboutResource {

    private final StoryService storyService;

    public AboutResource(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/p/stories")
    public BaseResponseDTO getAllStoriesWithPaging(@RequestBody BaseRequestDTO requestDTO) {
        return storyService.getAllStoriesWithPaging(requestDTO);
    }

    @GetMapping("/p/stories/{position}")
    public BaseResponseDTO getStoryByPosition(@PathVariable String position) {
        return storyService.getStory(position);
    }

    @GetMapping("/stories/{position}")
    public BaseResponseDTO getStoryDetail(@PathVariable String position) {
        return storyService.getStoryDetail(position);
    }

    @PutMapping("/stories")
    public BaseResponseDTO updateStory(@Valid @ModelAttribute UpdateStoryRequestDTO requestDTO) {
        return storyService.updateStory(requestDTO);
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
