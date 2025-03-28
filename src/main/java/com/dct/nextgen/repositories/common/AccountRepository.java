package com.dct.nextgen.repositories.common;

import com.dct.nextgen.dto.mapping.IAccountDTO;
import com.dct.nextgen.dto.mapping.IAuthenticationDTO;
import com.dct.nextgen.entity.base.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(
        value = """
            SELECT a.id, a.username, a.fullname, a.email, a.phone, a.status, a.device_id as deviceId
            FROM account a;
        """,
        nativeQuery = true
    )
    Page<IAccountDTO> findAllWithPaging(Pageable pageable);

    @Query(
        value = """
            SELECT a.id, a.username, a.fullname, a.email, a.phone, a.status, a.device_id as deviceId
            FROM account a LIMIT 100;
        """,
        nativeQuery = true
    )
    List<IAccountDTO> findAllNonPaging();

    @Query(
        value = """
            SELECT a.id, a.username, a.fullname, a.email, a.phone, a.address, a.status, a.device_id as deviceId
            FROM account a WHERE a.id = ?1
        """,
        nativeQuery = true
    )
    Optional<IAccountDTO> findIAccountById(Integer accountId);

    @Query(
        value = """
            SELECT a.id, a.username, a.password, a.email, a.status, a.device_id as deviceId
            FROM account a WHERE a.username = ?1
        """,
        nativeQuery = true
    )
    Optional<IAuthenticationDTO> findAuthenticationByUsername(String username);

    @Query(
        value = """
            SELECT a.id, a.username, a.password, a.email, a.status, a.device_id as deviceId
            FROM account a WHERE a.email = ?1
        """,
        nativeQuery = true
    )
    Optional<IAuthenticationDTO> findAuthenticationByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByUsernameOrEmailAndIdNot(String username, String email, Integer accountId);
}
