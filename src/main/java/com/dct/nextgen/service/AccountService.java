package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;

public interface AccountService {

    Account createNewAccount(CreateAccountRequestDTO request);

    BaseResponseDTO getAccountsWithPaging(BaseRequestDTO request);

    BaseResponseDTO getAccountDetail(Integer accountId);

    BaseResponseDTO updateAccount(UpdateAccountRequestDTO request);

    BaseResponseDTO updateAccountStatus(UpdateAccountStatusRequestDTO request);

    BaseResponseDTO deleteAccount(Integer accountId);
}
