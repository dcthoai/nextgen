package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.service.AccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/accounts")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public BaseResponseDTO getAccountsWithPaging(@RequestBody BaseRequestDTO request) {
        return accountService.getAccountsWithPaging(request);
    }

    @GetMapping("/{accountID}")
    public BaseResponseDTO getAccountDetail(@PathVariable Integer accountID) {
        return accountService.getAccountDetail(accountID);
    }

    @PostMapping
    public BaseResponseDTO createNewAccount(@RequestBody RegisterAccountRequestDTO request) {
        accountService.createNewAccount(request);
        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    public BaseResponseDTO updateAccount(@RequestBody UpdateAccountRequestDTO request) {
        return accountService.updateAccount(request);
    }

    @DeleteMapping("/{accountID}")
    public BaseResponseDTO deleteAccount(@PathVariable Integer accountID) {
        return accountService.deleteAccount(accountID);
    }
}
