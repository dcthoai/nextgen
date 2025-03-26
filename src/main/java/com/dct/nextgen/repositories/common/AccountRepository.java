package com.dct.nextgen.repositories.common;

import com.dct.nextgen.dto.mapping.IAccountDTO;
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
            SELECT a.ID as id, a.fullname, a.username, a.email, a.address, a.phone, a.status, a.token, a.device_ID as deviceId
            FROM account a
        """,
        nativeQuery = true
    )
    Page<IAccountDTO> findAllWithPaging(Pageable pageable);

    @Query(
        value = """
            SELECT a.ID as id, a.fullname, a.username, a.email, a.address, a.phone, a.status, a.token, a.device_ID as deviceId
            FROM account a LIMIT 100
        """,
        nativeQuery = true
    )
    List<IAccountDTO> findAllNonPaging();

    @Query(
        value = """
            SELECT a.ID as id, a.fullname, a.username, a.email, a.address, a.phone, a.status, a.token, a.device_ID as deviceId
            FROM account a WHERE a.ID = ?1
        """,
        nativeQuery = true
    )
    Optional<IAccountDTO> findAccountByID(Integer accountID);

    @Query(
        value = """
            SELECT a.ID as id, a.fullname, a.username, a.email, a.address, a.phone, a.status, a.token, a.device_ID as deviceId
            FROM account a WHERE a.username = ?1
        """,
        nativeQuery = true
    )
    Optional<IAccountDTO> findAccountByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByUsernameOrEmailAndIdNot(String username, String email, Integer accountID);
    Optional<Account> findByUsername(String username);
}
