package com.dct.nextgen.repositories;

import com.dct.nextgen.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByUsername(String username);
    Account findAccountByEmail(String email);
    Account findAccountByUsernameOrEmail(String username, String email);
}
