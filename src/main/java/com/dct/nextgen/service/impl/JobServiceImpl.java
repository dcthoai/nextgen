package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.company.JobDTO;
import com.dct.nextgen.dto.mapping.IJobDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateJobRequestDTO;
import com.dct.nextgen.dto.request.UpdateJobRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Job;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.JobRepository;
import com.dct.nextgen.service.JobService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private static final String ENTITY_NAME = "JobServiceImpl";

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public BaseResponseDTO getAllJobsWithPaging(BaseRequestDTO request) {
        Page<IJobDTO> jobsWithPaged = jobRepository.findAllWithPaging(request.getPageable());
        return BaseResponseDTO.builder().total(jobsWithPaged.getTotalElements()).ok(jobsWithPaged.getContent());
    }

    @Override
    public BaseResponseDTO getJobDetail(Integer jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (jobOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Job job = jobOptional.get();
        JobDTO jobDetail = new JobDTO();
        BeanUtils.copyProperties(job, jobDetail);
        Common.setAuditingInfo(job, jobDetail);

        return BaseResponseDTO.builder().ok(jobDetail);
    }

    @Override
    public BaseResponseDTO createJob(CreateJobRequestDTO request) {
        Job job = new Job();
        BeanUtils.copyProperties(request, job);
        jobRepository.save(job);
        return BaseResponseDTO.builder().ok(job);
    }

    @Override
    public BaseResponseDTO updateJob(UpdateJobRequestDTO request) {
        Optional<Job> jobOptional = jobRepository.findById(request.getId());

        if (jobOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.DATA_NOT_FOUND);
        }

        Job job = jobOptional.get();
        BeanUtils.copyProperties(request, job);
        jobRepository.save(job);

        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO deleteJob(Integer jobId) {
        if (Objects.isNull(jobId) || jobId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        jobRepository.deleteById(jobId);
        return BaseResponseDTO.builder().ok();
    }
}
