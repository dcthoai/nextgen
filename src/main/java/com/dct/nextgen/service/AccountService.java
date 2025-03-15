package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.RegisterRequestDTO;
import com.dct.nextgen.entity.Account;

public interface AccountService {

    Account isExistsUser(RegisterRequestDTO requestDTO);

    Account isExistsUser(String username, String email);

    Account findUserByUsername(String username);

    Account findByID(int userID);

    Account findUserByEmail(String email);

    Account create(RegisterRequestDTO requestDTO);

    Account createUserAccount(RegisterRequestDTO requestDTO);

    Account createAdminAccount();

    Account update(Account account);
}
