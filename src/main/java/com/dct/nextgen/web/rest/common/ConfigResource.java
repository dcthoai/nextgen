package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.dto.response.BaseResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configs")
public class ConfigResource {

    private static final Logger log = LoggerFactory.getLogger(ConfigResource.class);

    @GetMapping("/{configID}")
    public BaseResponseDTO getConfigDetail(@PathVariable Integer configID) {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    public BaseResponseDTO updateConfig() {

        return BaseResponseDTO.builder().ok();
    }
}
