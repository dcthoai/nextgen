package com.dct.nextgen.service.impl;

import com.dct.nextgen.common.Common;
import com.dct.nextgen.constants.AccountConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IAccountDTO;
import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.auth.AccountDTO;
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

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public BaseResponseDTO getAccountsWithPaging(BaseRequestDTO request) {
        if (request.getPageable().isPaged()) {
            Page<IAccountDTO> accountsWithPaged = accountRepository.findAllWithPaging(request.getPageable());
            List<IAccountDTO> accounts = accountsWithPaged.getContent();
            return BaseResponseDTO.builder().total(accountsWithPaged.getTotalElements()).ok(accounts);
        }

        return BaseResponseDTO.builder().ok(accountRepository.findAllNonPaging());
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
    public Account createNewAccount(RegisterAccountRequestDTO request) {
        boolean isExistedAccount = accountRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (isExistedAccount) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXISTED);
        }

        List<IRoleDTO> roles = roleRepository.findAllByIds(request.getRoleIds());

        if (roles.isEmpty() || roles.size() != request.getRoleIds().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_PERMISSION);
        }

        String rawPassword = request.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        Account account = Account.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(hashedPassword)
            .status(AccountConstants.STATUS.ACTIVE)
            .build();

        accountRepository.save(account);
        List<AccountRole> accountRoles = new ArrayList<>();
        roles.forEach(role -> accountRoles.add(new AccountRole(account.getId(), role.getId())));

        accountRoleRepository.saveAll(accountRoles);
        return account;
    }

    @Override
    public BaseResponseDTO updateAccount(UpdateAccountRequestDTO request) {
        boolean isExistedAccount = accountRepository.existsByUsernameOrEmailAndIdNot(
            request.getUsername(),
            request.getEmail(),
            request.getId()
        );

        if (isExistedAccount) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXISTED);
        }

        Optional<Account> accountOptional = accountRepository.findById(request.getId());

        if (accountOptional.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        List<Role> accountRolesForUpdate = roleRepository.findAllById(request.getRoleIds());

        if (accountRolesForUpdate.isEmpty() || accountRolesForUpdate.size() != request.getRoleIds().size()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_PERMISSION);
        }

        Account account = accountOptional.get();
        BeanUtils.copyProperties(request, account);
        account.setRoles(accountRolesForUpdate);
        accountRepository.save(account);

        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO deleteAccount(Integer accountId) {
        if (Objects.isNull(accountId) || accountId <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        accountRepository.deleteById(accountId);
        return BaseResponseDTO.builder().ok();
    }
}
