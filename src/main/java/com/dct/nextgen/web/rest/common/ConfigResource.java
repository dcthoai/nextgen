package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.response.BaseResponseDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configs")
@CheckAuthorize(authorities = RoleConstants.System.SYSTEM)
public class ConfigResource {

    @GetMapping("/{configId}")
    @CheckAuthorize(authorities = RoleConstants.System.VIEW)
    public BaseResponseDTO getConfigDetail(@PathVariable Integer configId) {
        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    @CheckAuthorize(authorities = RoleConstants.System.UPDATE)
    public BaseResponseDTO updateConfig() {
        return BaseResponseDTO.builder().ok();
    }
}
