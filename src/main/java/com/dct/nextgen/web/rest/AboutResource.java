package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdatePartnerRequestDTO;
import com.dct.nextgen.dto.request.UpdateStoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.PartnerService;
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
    private final PartnerService partnerService;

    public AboutResource(StoryService storyService, PartnerService partnerService) {
        this.storyService = storyService;
        this.partnerService = partnerService;
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

    @GetMapping("/p/partners")
    public BaseResponseDTO getAllPartnersWithPaging(@RequestBody BaseRequestDTO requestDTO) {
        return partnerService.getAllPartnersWithPaging(requestDTO);
    }

    @GetMapping("/partners/{partnerId}")
    public BaseResponseDTO getPartnerDetail(@PathVariable Integer partnerId) {
        return partnerService.getPartnerDetail(partnerId);
    }

    @PostMapping("/partners")
    public BaseResponseDTO createNewPartner(@Valid @ModelAttribute CreateOrUpdatePartnerRequestDTO requestDTO) {
        return partnerService.createNewPartner(requestDTO);
    }

    @PutMapping("/partners")
    public BaseResponseDTO updatePartner(@Valid @ModelAttribute CreateOrUpdatePartnerRequestDTO requestDTO) {
        return partnerService.updatePartner(requestDTO);
    }

    @DeleteMapping("/partners/{partnerId}")
    public BaseResponseDTO deletePartner(@PathVariable Integer partnerId) {
        return partnerService.deletePartner(partnerId);
    }
}
