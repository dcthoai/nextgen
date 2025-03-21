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
@RequestMapping("/api/jobs")
public class JobResource {

    private static final Logger log = LoggerFactory.getLogger(JobResource.class);

    @GetMapping
    public BaseResponseDTO getAllJobsWithPaging() {

        return new BaseResponseDTO();
    }

    @GetMapping("/{jobID}")
    public BaseResponseDTO getJobDetail(@PathVariable Integer jobID) {

        return new BaseResponseDTO();
    }

    @PostMapping
    public BaseResponseDTO createNewJob() {

        return new BaseResponseDTO();
    }

    @PutMapping
    public BaseResponseDTO updateJob() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/{jobID}")
    public BaseResponseDTO deleteJob(@PathVariable Integer jobID) {

        return new BaseResponseDTO();
    }
}
