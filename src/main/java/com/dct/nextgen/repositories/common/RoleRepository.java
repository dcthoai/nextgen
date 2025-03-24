package com.dct.nextgen.repositories.common;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.entity.base.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(
        value = """
            SELECT r.ID, r.name, r.code
            FROM role r
            JOIN account_role ar on r.ID = ar.role_ID
            WHERE ar.account_ID = ?1
        """,
        nativeQuery = true
    )
    List<IRoleDTO> findAllByAccountID(Integer accountID);

    @Query(value = "SELECT r.id as ID, r.name, r.code FROM role r", nativeQuery = true)
    Page<IRoleDTO> findAllWithPaging(Pageable pageable);

    @Query(value = "SELECT r.id as ID, r.name, r.code FROM role r LIMIT 100", nativeQuery = true)
    List<IRoleDTO> findAllNonPaging();

    @Query(value = "SELECT r.id as ID, r.name, r.code FROM role r WHERE r.ID in (?1)", nativeQuery = true)
    List<IRoleDTO> findAllByIDs(Iterable<Integer> roleIDs);

    @Query(value = "SELECT r.id as ID, r.name, r.code FROM role r WHERE r.ID = ?1", nativeQuery = true)
    Optional<IRoleDTO> findByID(Integer roleID);

    boolean existsByCodeOrName(String code, String name);
    boolean existsByCodeOrNameAndIdNot(String code, String name, Integer id);
}
