package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateJobRequestDTO;
import com.dct.nextgen.dto.request.UpdateJobRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface JobService {

    BaseResponseDTO getAllJobsWithPaging(BaseRequestDTO request);
    BaseResponseDTO getJobDetail(Integer jobId);
    BaseResponseDTO createJob(CreateJobRequestDTO request);
    BaseResponseDTO updateJob(UpdateJobRequestDTO request);
    BaseResponseDTO deleteJob(Integer jobId);
}
