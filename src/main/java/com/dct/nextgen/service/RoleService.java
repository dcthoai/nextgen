package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateRoleRequestDTO;
import com.dct.nextgen.dto.request.UpdateRoleRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;

public interface RoleService {

    BaseResponseDTO getRolesWithPaging(BaseRequestDTO request);
    BaseResponseDTO getRoleDetail(Integer roleID);
    BaseResponseDTO getPermissionTree();
    BaseResponseDTO getAccountRoles(Integer userID);
    BaseResponseDTO createNewRole(CreateRoleRequestDTO request);
    BaseResponseDTO updateRole(UpdateRoleRequestDTO request);
    BaseResponseDTO deleteRole(Integer roleID);
}
