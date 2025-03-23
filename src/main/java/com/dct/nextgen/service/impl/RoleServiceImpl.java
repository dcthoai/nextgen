package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.BaseCommon;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.ResultConstants;
import com.dct.nextgen.dto.auth.PermissionDTO;
import com.dct.nextgen.dto.auth.PermissionTreeNode;
import com.dct.nextgen.dto.auth.RoleDTO;
import com.dct.nextgen.dto.mapping.IPermissionDTO;
import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateRoleRequestDTO;
import com.dct.nextgen.dto.request.UpdateRoleRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Permission;
import com.dct.nextgen.entity.base.Role;
import com.dct.nextgen.entity.base.RolePermission;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.common.PermissionRepository;
import com.dct.nextgen.repositories.common.RolePermissionRepository;
import com.dct.nextgen.repositories.common.RoleRepository;
import com.dct.nextgen.service.RoleService;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String ENTITY_NAME = "RoleServiceImpl";
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final BaseCommon baseCommon;

    public RoleServiceImpl(RolePermissionRepository rolePermissionRepository,
                           PermissionRepository permissionRepository,
                           RoleRepository roleRepository,
                           BaseCommon baseCommon) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.baseCommon = baseCommon;
    }

    @Override
    public BaseResponseDTO getRolesWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IRoleDTO> rolesWithPaged = roleRepository.findAllWithPaging(request.getPageable());
            return BaseResponseDTO.builder().total(rolesWithPaged.getTotalElements()).ok(rolesWithPaged.getContent());
        }

        return BaseResponseDTO.builder().ok(roleRepository.findAllNonPaging());
    }

    @Override
    public BaseResponseDTO getRoleDetail(Integer roleID) {
        Optional<IRoleDTO> roleDTO = roleRepository.findByID(roleID);

        if (roleDTO.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ResultConstants.DATA_NOT_FOUND);
        }

        List<PermissionDTO> rolePermissions = permissionRepository.findAllByRoleID(roleID)
            .stream()
            .map(iPermissionDTO -> {
                PermissionDTO permissionDTO = new PermissionDTO(iPermissionDTO);

                permissionDTO.setName(baseCommon.getMessageI18n(permissionDTO.getName()));
                permissionDTO.setDescription(baseCommon.getMessageI18n(permissionDTO.getDescription()));

                return permissionDTO;
            })
            .toList();

        RoleDTO roleDetails = new RoleDTO(roleDTO.get());
        roleDetails.setRolePermissions(rolePermissions);

        return BaseResponseDTO.builder().ok(roleDetails);
    }

    @Override
    public BaseResponseDTO getPermissionTree() {
        List<IPermissionDTO> permissions = permissionRepository.findAllByOrderByCodeAsc();
        Map<String, PermissionTreeNode> permissionTreeMap = new HashMap<>();
        List<PermissionTreeNode> roots = new ArrayList<>();

        for (IPermissionDTO permission : permissions) {
            PermissionTreeNode node = PermissionTreeNode.builder()
                .id(permission.getId())
                .name(baseCommon.getMessageI18n(permission.getName()))
                .code(permission.getCode())
                .parentId(permission.getParentId())
                .build();

            if (Objects.isNull(permission.getParentCode())) {
                permissionTreeMap.put(node.getCode(), node);
                roots.add(node);
            } else {
                PermissionTreeNode parentNode = permissionTreeMap.get(permission.getParentCode());

                if (Objects.nonNull(parentNode)) {
                    parentNode.appendChildren(node);
                    permissionTreeMap.put(node.getCode(), node);
                }
            }
        }

        List<PermissionTreeNode> results = roots.stream()
            .map(node -> permissionTreeMap.get(node.getCode()))
            .collect(Collectors.toList());

        return BaseResponseDTO.builder().ok(results);
    }

    @Override
    public BaseResponseDTO getAccountRoles(Integer accountID) {
        if (Objects.isNull(accountID) || accountID <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        return BaseResponseDTO.builder().ok(roleRepository.findAllByAccountID(accountID));
    }

    @Override
    @Transactional
    public BaseResponseDTO createNewRole(CreateRoleRequestDTO request) {
        boolean isRoleExisted = roleRepository.existsByCodeOrName(request.getCode(), request.getName());

        if (isRoleExisted) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ROLE_EXISTED);
        }

        List<RolePermission> rolePermissions = new ArrayList<>();
        Role role = roleRepository.save(new Role(request.getName(), request.getCode()));

        for (Integer permissionID : request.getPermissionIDs()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleID(role.getId());
            rolePermission.setPermissionID(permissionID);
            rolePermissions.add(rolePermission);
        }

        rolePermissionRepository.saveAll(rolePermissions);
        return BaseResponseDTO.builder().ok();
    }

    @Override
    @Transactional
    public BaseResponseDTO updateRole(UpdateRoleRequestDTO request) {
        Optional<Role> roleOptional = roleRepository.findById(request.getId());

        if (roleOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ResultConstants.DATA_NOT_FOUND);
        }

        Role role = roleOptional.get();

        // If the updated role content already exists
        if (roleRepository.existsByCodeOrNameAndIdNot(request.getCode(), request.getName(), role.getId())) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ROLE_EXISTED);
        }

        List<Permission> permissionsForUpdate = permissionRepository.findAllById(request.getPermissionIDs());

        if (permissionsForUpdate.size() != request.getPermissionIDs().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_PERMISSION);
        }

        role.setName(request.getName());
        role.setCode(request.getCode());
        role.setPermissions(permissionsForUpdate);
        roleRepository.save(role);

        return BaseResponseDTO.builder().ok();
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteRole(Integer roleID) {
        if (Objects.isNull(roleID) || roleID <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        roleRepository.deleteById(roleID);
        return BaseResponseDTO.builder().ok();
    }
}
