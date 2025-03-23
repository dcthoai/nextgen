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
@RequestMapping("/api/staffs")
public class StaffResource {

    private static final Logger log = LoggerFactory.getLogger(StaffResource.class);

    @GetMapping
    public BaseResponseDTO getAllStaffsWithPaging() {

        return BaseResponseDTO.builder().ok();
    }

    @GetMapping("/{staffID}")
    public BaseResponseDTO getStaffDetail(@PathVariable Integer staffID) {

        return BaseResponseDTO.builder().ok();
    }

    @PostMapping
    public BaseResponseDTO createNewStaff() {

        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    public BaseResponseDTO updateStaff() {

        return BaseResponseDTO.builder().ok();
    }

    @DeleteMapping("/{staffID}")
    public BaseResponseDTO deleteStaff(@PathVariable Integer staffID) {

        return BaseResponseDTO.builder().ok();
    }
}
