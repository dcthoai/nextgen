package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.BaseCommon;
import com.dct.nextgen.common.DataUtils;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.auth.PermissionDTO;
import com.dct.nextgen.dto.auth.PermissionTreeNode;
import com.dct.nextgen.dto.mapping.IPermissionDTO;
import com.dct.nextgen.dto.auth.RoleDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateRoleRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Role;
import com.dct.nextgen.entity.base.RolePermission;
import com.dct.nextgen.exception.BaseException;
import com.dct.nextgen.repositories.common.PermissionRepository;
import com.dct.nextgen.repositories.common.RolePermissionRepository;
import com.dct.nextgen.repositories.common.RoleRepository;
import com.dct.nextgen.service.RoleService;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    private static final String ENTITY_NAME = "RoleServiceImpl";
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final BaseCommon baseCommon;
    private final EntityManager entityManager;

    public RoleServiceImpl(RolePermissionRepository rolePermissionRepository,
                           PermissionRepository permissionRepository,
                           RoleRepository roleRepository,
                           BaseCommon baseCommon,
                           EntityManager entityManager) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.baseCommon = baseCommon;
        this.entityManager = entityManager;
    }

    @Override
    public BaseResponseDTO getRolesWithPaging(BaseRequestDTO request) {
        DataUtils.QueryBuilder queryBuilder = DataUtils.createQueryBuilder(entityManager)
            .querySql("SELECT id, name, code FROM role where id = 1");

        long totalRecords = queryBuilder.countTotalRecords();

        if (totalRecords > 0) {
            List<RoleDTO> roles = queryBuilder.getResultsWithDTO(RoleDTO::new);
            return BaseResponseDTO.builder().total(totalRecords).ok(roles);
        }

        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO getRoleDetail(Integer roleID) {
        return null;
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
                .checked(false)
                .collapsed(false)
                .disabled(false)
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
            throw BaseException.builder()
                .entityName(ENTITY_NAME)
                .errorKey(ExceptionConstants.INVALID_REQUEST_DATA)
                .buildBadRequestException();
        }

        return BaseResponseDTO.builder().ok(roleRepository.findAllByAccountID(accountID));
    }

    @Override
    public BaseResponseDTO createNewRole(CreateRoleRequestDTO request) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        Role role = roleRepository.save(new Role(request.getName(), request.getCode()));

        for (PermissionDTO permission : request.getPermissions()) {
            RolePermission rolePermission = new RolePermission();

            rolePermission.setRoleID(role.getId());
            rolePermission.setPermissionID(permission.getID());
            rolePermission.setPermissionCode(permission.getCode());

            rolePermissions.add(rolePermission);
        }

        rolePermissionRepository.saveAll(rolePermissions);
        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO updateRole() {
        return null;
    }

    @Override
    public BaseResponseDTO deleteRole(Integer roleID) {
        if (Objects.isNull(roleID) || roleID <= 0) {
            throw BaseException.builder()
                .entityName(ENTITY_NAME)
                .errorKey(ExceptionConstants.INVALID_REQUEST_DATA)
                .buildBadRequestException();
        }

        roleRepository.deleteById(roleID);
        return BaseResponseDTO.builder().ok();
    }
}
