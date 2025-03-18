package com.dct.nextgen.web.rest.common;

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
@RequestMapping("/api/roles")
public class RoleResource {

    private static final Logger log = LoggerFactory.getLogger(RoleResource.class);

    @GetMapping
    public BaseResponseDTO getAllRolesWithPaging() {

        return BaseResponseDTO.builder().build();
    }

    @GetMapping("/{roleID}")
    public BaseResponseDTO getRoleDetail(@PathVariable Integer roleID) {

        return BaseResponseDTO.builder().build();
    }

    @PostMapping
    public BaseResponseDTO createNewRole() {

        return BaseResponseDTO.builder().build();
    }

    @PutMapping
    public BaseResponseDTO updateRole() {

        return BaseResponseDTO.builder().build();
    }

    @DeleteMapping("/{roleID}")
    public BaseResponseDTO deleteRole(@PathVariable Integer roleID) {

        return BaseResponseDTO.builder().build();
    }
}
