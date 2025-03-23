package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.RoleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/roles")
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public BaseResponseDTO getAllRolesWithPaging(@RequestBody BaseRequestDTO request) {
        return roleService.getRolesWithPaging(request);
    }

    @GetMapping("/permissions")
    public BaseResponseDTO getPermissionTree() {
        return roleService.getPermissionTree();
    }

    @GetMapping("/{roleID}")
    public BaseResponseDTO getRoleDetail(@PathVariable Integer roleID) {
        return roleService.getRoleDetail(roleID);
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
        return roleService.deleteRole(roleID);
    }
}
