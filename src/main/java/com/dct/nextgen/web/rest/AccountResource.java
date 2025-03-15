package com.dct.nextgen.web.rest;

import com.dct.nextgen.dto.response.AccountDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.Account;
import com.dct.nextgen.service.AccountService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common/users")
public class AccountResource {

    private static final Logger log = LoggerFactory.getLogger(AccountResource.class);
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public AccountResource(AccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("{userID}")
    public BaseResponseDTO getAccountInfo(@PathVariable int userID) {
        log.debug("REST request to get account info: {}", userID);
        Account account = accountService.findByID(userID);
        AccountDTO accountDTO = objectMapper.convertValue(account, AccountDTO.class);
        return new BaseResponseDTO(accountDTO);
    }
}
