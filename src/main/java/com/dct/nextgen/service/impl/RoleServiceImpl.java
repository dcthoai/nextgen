package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.common.MessageUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.ResultConstants;
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
import org.springframework.beans.BeanUtils;
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
    private final MessageUtils messageUtils;

    public RoleServiceImpl(RolePermissionRepository rolePermissionRepository,
                           PermissionRepository permissionRepository,
                           RoleRepository roleRepository,
                           MessageUtils messageUtils) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.messageUtils = messageUtils;
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
    public BaseResponseDTO getRoleDetail(Integer roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        if (roleOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ResultConstants.DATA_NOT_FOUND);
        }

        Role role = roleOptional.get();
        RoleDTO roleDetails = new RoleDTO();
        BeanUtils.copyProperties(role, roleDetails);
        Common.setAuditingInfo(role, roleDetails);
        roleDetails.setPermissions(permissionRepository.findAllByRoleId(roleId));

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
                .name(messageUtils.getMessageI18n(permission.getName()))
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
    public List<IRoleDTO> getAccountRoles(Integer accountId) {
        if (Objects.isNull(accountId) || accountId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        return roleRepository.findAllByAccountId(accountId);
    }

    @Override
    @Transactional
    public BaseResponseDTO createNewRole(CreateRoleRequestDTO request) {
        boolean isRoleExisted = roleRepository.existsByCodeOrName(request.getCode(), request.getName());

        if (isRoleExisted) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ROLE_EXISTED);
        }

        Role role = roleRepository.save(new Role(request.getName(), request.getCode()));
        List<IPermissionDTO> permissions = permissionRepository.findAllByIds(request.getPermissionIds());
        List<RolePermission> rolePermissions = new ArrayList<>();

        if (permissions.isEmpty() || permissions.size() != request.getPermissionIds().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_PERMISSION);
        }

        permissions.forEach(permission -> rolePermissions.add(new RolePermission(role.getId(), permission.getId())));
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

        List<Permission> permissionsForUpdate = permissionRepository.findAllById(request.getPermissionIds());

        if (permissionsForUpdate.isEmpty() || permissionsForUpdate.size() != request.getPermissionIds().size()) {
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
    public BaseResponseDTO deleteRole(Integer roleId) {
        if (Objects.isNull(roleId) || roleId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        roleRepository.deleteById(roleId);
        return BaseResponseDTO.builder().ok();
    }
}
