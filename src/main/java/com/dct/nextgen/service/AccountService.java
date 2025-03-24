package com.dct.nextgen.service;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;

public interface AccountService {

    Account createNewAccount(RegisterAccountRequestDTO request);

    BaseResponseDTO getAccountsWithPaging(BaseRequestDTO request);

    BaseResponseDTO getAccountDetail(Integer accountID);

    BaseResponseDTO updateAccount(UpdateAccountRequestDTO request);

    BaseResponseDTO deleteAccount(Integer accountID);
}
