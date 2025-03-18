package com.dct.nextgen.repositories;

import com.dct.nextgen.dto.auth.PermissionDTO;
import com.dct.nextgen.entity.base.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query(value =
        "select p.ID, p.name, p.code, p.description, p.parent_ID as parentID, p.parent_code as parentCode " +
        "from permission p " +
        "join role_permission rp on p.ID = rp.permission_ID " +
        "join account_role ar on ar.role_ID = rp.role_ID " +
        "where ar.account_ID = ?1", nativeQuery = true)
    Set<PermissionDTO> findAllByUserID(int userID);
}
