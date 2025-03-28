package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateJobRequestDTO;
import com.dct.nextgen.dto.request.UpdateJobRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.JobService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobResource {

    private final JobService jobService;

    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/p/jobs")
    public BaseResponseDTO getAllJobsWithPaging(@RequestBody BaseRequestDTO requestDTO) {
        return jobService.getAllJobsWithPaging(requestDTO);
    }

    @GetMapping("/jobs/{jobId}")
    public BaseResponseDTO getJobDetail(@PathVariable Integer jobId) {
        return jobService.getJobDetail(jobId);
    }

    @PostMapping("/jobs")
    public BaseResponseDTO createNewJob(@Valid @RequestBody CreateJobRequestDTO requestDTO) {
        return jobService.createJob(requestDTO);
    }

    @PutMapping("/jobs")
    public BaseResponseDTO updateJob(@Valid @RequestBody UpdateJobRequestDTO requestDTO) {
        return jobService.updateJob(requestDTO);
    }

    @DeleteMapping("/jobs/{jobId}")
    public BaseResponseDTO deleteJob(@PathVariable Integer jobId) {
        return jobService.deleteJob(jobId);
    }
}
