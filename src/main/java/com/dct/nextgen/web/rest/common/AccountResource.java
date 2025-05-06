package com.dct.nextgen.web.rest.common;

import com.dct.nextgen.aop.annotation.CheckAuthorize;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.constants.RoleConstants;
import com.dct.nextgen.dto.auth.AccountDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountStatusRequestDTO;
import com.dct.nextgen.dto.response.AuthenticationResponseDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.common.PermissionRepository;
import com.dct.nextgen.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/common/accounts")
@CheckAuthorize(authorities = RoleConstants.Account.ACCOUNT)
public class AccountResource {

    private final AccountService accountService;
    private final PermissionRepository permissionRepository;
    private static final String ENTITY_NAME = "AccountResource";

    public AccountResource(AccountService accountService, PermissionRepository permissionRepository) {
        this.accountService = accountService;
        this.permissionRepository = permissionRepository;
    }

    @GetMapping
    @CheckAuthorize(authorities = RoleConstants.Account.VIEW)
    public BaseResponseDTO getAccountsWithPaging(@ModelAttribute BaseRequestDTO request) {
        return accountService.getAccountsWithPaging(request);
    }

    @GetMapping("/{accountId}")
    @CheckAuthorize(authorities = RoleConstants.Account.VIEW)
    public BaseResponseDTO getAccountDetail(@PathVariable Integer accountId) {
        return accountService.getAccountDetail(accountId);
    }

    @PostMapping
    @CheckAuthorize(authorities = RoleConstants.Account.CREATE)
    public BaseResponseDTO createNewAccount(@Valid @RequestBody CreateAccountRequestDTO request) {
        accountService.createNewAccount(request);
        return BaseResponseDTO.builder().ok();
    }

    @PutMapping
    @CheckAuthorize(authorities = RoleConstants.Account.UPDATE)
    public BaseResponseDTO updateAccount(@Valid @RequestBody UpdateAccountRequestDTO request) {
        return accountService.updateAccount(request);
    }

    @PostMapping("/status")
    public BaseResponseDTO checkAuthenticationStatus() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (StringUtils.hasText(username)) {
            AccountDTO accountDTO = accountService.findAccountByUsername(username);
            Set<String> authorities = permissionRepository.findAllByAccountId(accountDTO.getId());
            AuthenticationResponseDTO authenticationDTO = new AuthenticationResponseDTO();
            BeanUtils.copyProperties(accountDTO, authenticationDTO);
            authenticationDTO.setAuthorities(authorities);
            return BaseResponseDTO.builder().ok(authenticationDTO);
        }

        throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.BAD_CREDENTIALS);
    }

    @PutMapping("/status")
    @CheckAuthorize(authorities = RoleConstants.Account.UPDATE)
    public BaseResponseDTO updateAccountStatus(@Valid @RequestBody UpdateAccountStatusRequestDTO request) {
        return accountService.updateAccountStatus(request);
    }

    @DeleteMapping("/{accountId}")
    @CheckAuthorize(authorities = RoleConstants.Account.DELETE)
    public BaseResponseDTO deleteAccount(@PathVariable Integer accountId) {
        return accountService.deleteAccount(accountId);
    }
}
