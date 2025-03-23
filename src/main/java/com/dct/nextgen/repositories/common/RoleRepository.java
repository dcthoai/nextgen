package com.dct.nextgen.repositories.common;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.entity.base.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(
        value = """
            SELECT r.ID, r.name, r.code
            FROM role r
            JOIN account_role ar on r.ID = ar.role_ID
            WHERE ar.account_ID = :accountID
        """,
        nativeQuery = true
    )
    List<IRoleDTO> findAllByAccountID(Integer accountID);
}
