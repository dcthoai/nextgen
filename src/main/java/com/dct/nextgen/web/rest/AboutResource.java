package com.dct.nextgen.web.rest;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateOrUpdatePartnerRequestDTO;
import com.dct.nextgen.dto.request.UpdateContactFormRequestDTO;
import com.dct.nextgen.dto.request.UpdateStoryRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.ContactFormService;
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
    private final ContactFormService contactFormService;

    public AboutResource(StoryService storyService,
                         PartnerService partnerService,
                         ContactFormService contactFormService) {
        this.storyService = storyService;
        this.partnerService = partnerService;
        this.contactFormService = contactFormService;
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
    @CheckAuthorize(authorities = RoleConstants.About.Story.VIEW)
    public BaseResponseDTO getStoryDetail(@PathVariable String position) {
        return storyService.getStoryDetail(position);
    }

    @PutMapping("/stories")
    @CheckAuthorize(authorities = RoleConstants.About.Story.UPDATE)
    public BaseResponseDTO updateStory(@Valid @ModelAttribute UpdateStoryRequestDTO requestDTO) {
        return storyService.updateStory(requestDTO);
    }

    @GetMapping("/p/mottos")
    public BaseResponseDTO getAllMottosWithPaging() {
        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/mottos/{mottoId}")
    @CheckAuthorize(authorities = RoleConstants.About.Motto.VIEW)
    public BaseResponseDTO getMottoDetail(@PathVariable Integer mottoId) {
        return BaseResponseDTO.builder().ok();
    }

    @PostMapping("/mottos")
    @CheckAuthorize(authorities = RoleConstants.About.Motto.CREATE)
    public BaseResponseDTO createNewMotto() {
        return BaseResponseDTO.builder().ok();
    }

    @PutMapping("/mottos")
    @CheckAuthorize(authorities = RoleConstants.About.Motto.UPDATE)
    public BaseResponseDTO updateMotto() {
        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/mottos/{mottoId}")
    @CheckAuthorize(authorities = RoleConstants.About.Motto.DELETE)
    public BaseResponseDTO deleteMotto(@PathVariable Integer mottoId) {
        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/p/contact-form")
    public BaseResponseDTO getFormContactInfo() {
        return contactFormService.getContactFormInfo();
    }

    @GetMapping("/contact-form")
    @CheckAuthorize(authorities = RoleConstants.About.ContactForm.VIEW)
    public BaseResponseDTO getFormContactDetail() {
        return contactFormService.getContactFormDetail();
    }

    @PutMapping("/contact-form")
    @CheckAuthorize(authorities = RoleConstants.About.ContactForm.UPDATE)
    public BaseResponseDTO updateContactFormInfo(@Valid @RequestBody UpdateContactFormRequestDTO requestDTO) {
        return contactFormService.updateContactForm(requestDTO);
    }

    @GetMapping("/p/partners")
    public BaseResponseDTO getAllPartnersWithPaging(@RequestBody BaseRequestDTO requestDTO) {
        return partnerService.getAllPartnersWithPaging(requestDTO);
    }

    @GetMapping("/partners/{partnerId}")
    @CheckAuthorize(authorities = RoleConstants.About.Partner.VIEW)
    public BaseResponseDTO getPartnerDetail(@PathVariable Integer partnerId) {
        return partnerService.getPartnerDetail(partnerId);
    }

    @PostMapping("/partners")
    @CheckAuthorize(authorities = RoleConstants.About.Partner.CREATE)
    public BaseResponseDTO createNewPartner(@Valid @ModelAttribute CreateOrUpdatePartnerRequestDTO requestDTO) {
        return partnerService.createNewPartner(requestDTO);
    }

    @PutMapping("/partners")
    @CheckAuthorize(authorities = RoleConstants.About.Partner.UPDATE)
    public BaseResponseDTO updatePartner(@Valid @ModelAttribute CreateOrUpdatePartnerRequestDTO requestDTO) {
        return partnerService.updatePartner(requestDTO);
    }

    @DeleteMapping("/partners/{partnerId}")
    @CheckAuthorize(authorities = RoleConstants.About.Partner.DELETE)
    public BaseResponseDTO deletePartner(@PathVariable Integer partnerId) {
        return partnerService.deletePartner(partnerId);
    }
}
