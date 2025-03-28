package com.dct.nextgen.service;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateRoleRequestDTO;
import com.dct.nextgen.dto.request.UpdateRoleRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

import java.util.List;

public interface RoleService {

    BaseResponseDTO getRolesWithPaging(BaseRequestDTO request);
    BaseResponseDTO getRoleDetail(Integer roleId);
    BaseResponseDTO getPermissionTree();
    BaseResponseDTO createNewRole(CreateRoleRequestDTO request);
    BaseResponseDTO updateRole(UpdateRoleRequestDTO request);
    BaseResponseDTO deleteRole(Integer roleId);
    List<IRoleDTO> getAccountRoles(Integer accountId);
}
