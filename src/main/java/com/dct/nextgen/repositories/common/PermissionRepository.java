package com.dct.nextgen.repositories.common;

import com.dct.nextgen.dto.mapping.IPermissionDTO;
import com.dct.nextgen.entity.base.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query(
        value = """
            SELECT p.ID as id, p.name, p.code, p.parent_ID as parentId, p.parent_code as parentCode
            FROM permission p
            ORDER BY p.code;
        """,
        nativeQuery = true
    )
    List<IPermissionDTO> findAllByOrderByCodeAsc();

    @Query(
        value = """
            SELECT p.ID as id, p.name, p.code, p.description, p.parent_ID as parentId, p.parent_code as parentCode
            FROM permission p
            JOIN role_permission rp on p.ID = rp.permission_ID
            JOIN account_role ar on ar.role_ID = rp.role_ID
            WHERE ar.account_ID = ?1
        """,
        nativeQuery = true
    )
    List<IPermissionDTO> findAllByAccountID(Integer accountID);

    @Query(
        value = """
            SELECT p.ID as id, p.name, p.code, p.description, p.parent_ID as parentId, p.parent_code as parentCode
            FROM permission p
            JOIN role_permission rp on p.ID = rp.permission_ID
            WHERE rp.role_ID = ?1
        """,
        nativeQuery = true
    )
    List<IPermissionDTO> findAllByRoleID(Integer roleID);

    @Query(value = "SELECT p.ID as id, p.name, p.code FROM permission p WHERE p.ID in (?1);", nativeQuery = true)
    List<IPermissionDTO> findAllByIDs(Iterable<Integer> permissionIDs);
}
