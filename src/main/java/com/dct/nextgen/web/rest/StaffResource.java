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

        return new BaseResponseDTO();
    }

    @GetMapping("/{staffID}")
    public BaseResponseDTO getStaffDetail(@PathVariable Integer staffID) {

        return new BaseResponseDTO();
    }

    @PostMapping
    public BaseResponseDTO createNewStaff() {

        return new BaseResponseDTO();
    }

    @PutMapping
    public BaseResponseDTO updateStaff() {

        return new BaseResponseDTO();
    }

    @DeleteMapping("/{staffID}")
    public BaseResponseDTO deleteStaff(@PathVariable Integer staffID) {

        return new BaseResponseDTO();
    }
}
