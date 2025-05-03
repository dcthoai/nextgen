package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.AccountConstants;
import com.dct.nextgen.constants.BaseConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IAccountDTO;
import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.request.AccountFilterSearchRequestDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.CreateAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.auth.AccountDTO;
import com.dct.nextgen.dto.request.UpdateAccountStatusRequestDTO;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.entity.base.Account;
import com.dct.nextgen.entity.base.AccountRole;
import com.dct.nextgen.entity.base.Role;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.repositories.common.AccountRepository;
import com.dct.nextgen.repositories.common.AccountRoleRepository;
import com.dct.nextgen.repositories.common.RoleRepository;
import com.dct.nextgen.service.AccountService;
import com.dct.nextgen.service.RoleService;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String ENTITY_NAME = "AccountServiceImpl";
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final AccountRoleRepository accountRoleRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              PasswordEncoder passwordEncoder,
                              RoleService roleService,
                              RoleRepository roleRepository,
                              AccountRoleRepository accountRoleRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public BaseResponseDTO getAccountsWithPaging(AccountFilterSearchRequestDTO request) {
        String fromDate = request.getFromDateSearch(), toDate = request.getToDateSearch();
        String status = request.getStatus(), keyword = request.getKeyword();

        if (Objects.nonNull(status) && !status.matches(BaseConstants.REGEX.ACCOUNT_STATUS_PATTERN)) {
            status = null;
        }

        if (StringUtils.hasText(keyword)) {
            keyword = "%" + keyword + "%";
        } else {
            keyword = null;
        }

        if (request.getPageable().isPaged()) {
            Page<IAccountDTO> accountsWithPaged = accountRepository.findAllWithPaging(
                status,
                keyword,
                fromDate,
                toDate,
                request.getPageable()
            );
            List<IAccountDTO> accounts = accountsWithPaged.getContent();
            return BaseResponseDTO.builder().total(accountsWithPaged.getTotalElements()).ok(accounts);
        }

        return BaseResponseDTO.builder().ok(accountRepository.findAllNonPaging(status, keyword, fromDate, toDate));
    }

    @Override
    public BaseResponseDTO getAccountDetail(Integer accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        Account account = accountOptional.get();
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        Common.setAuditingInfo(account, accountDTO);
        accountDTO.setAccountRoles(roleService.getAccountRoles(accountId));

        return BaseResponseDTO.builder().ok(accountDTO);
    }

    @Override
    @Transactional
    public Account createNewAccount(CreateAccountRequestDTO request) {
        boolean isExistedAccount = accountRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (isExistedAccount) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXISTED);
        }

        List<IRoleDTO> roles = roleRepository.findAllByIds(request.getRoleIds());

        if (roles.isEmpty() || roles.size() != request.getRoleIds().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ROLE_PERMISSION_INVALID);
        }

        String rawPassword = request.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        account.setPassword(hashedPassword);
        account.setStatus(AccountConstants.STATUS.ACTIVE);
        accountRepository.save(account);

        List<AccountRole> accountRoles = new ArrayList<>();
        roles.forEach(role -> accountRoles.add(new AccountRole(account.getId(), role.getId())));
        accountRoleRepository.saveAll(accountRoles);

        return account;
    }

    @Override
    public AccountDTO findAccountByUsername(String username) {
        Optional<IAccountDTO> accountOptional = accountRepository.findByAccountByUsername(username);

        if (accountOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(accountOptional.get(), accountDTO);

        return accountDTO;
    }

    @Override
    @Transactional
    public BaseResponseDTO updateAccount(UpdateAccountRequestDTO request) {
        Long existedAccounts = accountRepository.countByUsernameOrEmailAndIdNot(
            request.getUsername(),
            request.getEmail(),
            request.getId()
        );

        if (existedAccounts > 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXISTED);
        }

        Optional<Account> accountOptional = accountRepository.findById(request.getId());

        if (accountOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        List<Role> accountRolesForUpdate = roleRepository.findAllById(request.getRoleIds());

        if (accountRolesForUpdate.isEmpty() || accountRolesForUpdate.size() != request.getRoleIds().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ROLE_PERMISSION_INVALID);
        }

        Account account = accountOptional.get();
        BeanUtils.copyProperties(request, account);
        account.setRoles(accountRolesForUpdate);
        accountRepository.save(account);

        return BaseResponseDTO.builder().ok();
    }

    @Override
    @Transactional
    public BaseResponseDTO updateAccountStatus(UpdateAccountStatusRequestDTO request) {
        accountRepository.updateAccountStatusById(request.getAccountId(), request.getStatus());
        return BaseResponseDTO.builder().ok();
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteAccount(Integer accountId) {
        if (Objects.isNull(accountId) || accountId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        accountRepository.updateAccountStatusById(accountId, AccountConstants.STATUS.DELETED);
        return BaseResponseDTO.builder().ok();
    }
}
