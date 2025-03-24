package com.dct.nextgen.service.impl;

import com.dct.nextgen.constants.AccountConstants;
import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IAccountDTO;
import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.request.BaseRequestDTO;
import com.dct.nextgen.dto.request.RegisterAccountRequestDTO;
import com.dct.nextgen.dto.request.UpdateAccountRequestDTO;
import com.dct.nextgen.dto.response.AccountDTO;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
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
    public BaseResponseDTO getAccountDetail(Integer accountID) {
        Optional<IAccountDTO> iAccountDTO = accountRepository.findAccountByID(accountID);

        if (iAccountDTO.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(iAccountDTO.get(), accountDTO);
        List<IRoleDTO> accountRoles = roleService.getAccountRoles(accountID);
        accountDTO.setAccountRoles(accountRoles);

        return BaseResponseDTO.builder().ok(accountDTO);
    }

    @Override
    public Account createNewAccount(RegisterAccountRequestDTO request) {
        boolean isExistedAccount = accountRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (isExistedAccount) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_EXISTED);
        }

        List<Role> roles = roleRepository.findAllById(request.getRoleIDs());

        if (roles.isEmpty() || roles.size() != request.getRoleIDs().size()) {
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
        Optional<Account> account = accountRepository.findById(request.getID());

        if (account.isEmpty()) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
        }

        return BaseResponseDTO.builder().ok();
    }

    @Override
    public BaseResponseDTO deleteAccount(Integer accountID) {
        if (Objects.isNull(accountID) || accountID <= 0) {
            throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.INVALID_REQUEST_DATA);
        }

        accountRepository.deleteById(accountID);
        return BaseResponseDTO.builder().ok();
    }

    @Override
    public Account isExistsUser(RegisterAccountRequestDTO requestDTO) {
        return isExistsUser(requestDTO.getUsername(), requestDTO.getEmail());
    }

    @Override
    public Account isExistsUser(String username, String email) {
        return accountRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public Account findUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account findByID(int userID) {
        return accountRepository.findById(userID).orElse(null);
    }

    @Override
    public Account findUserByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account update(Account account) {
        log.debug("Updating account: {}", account.getUsername());

        if (accountRepository.existsById(account.getId()))
            return accountRepository.save(account);

        throw new BaseBadRequestException(ENTITY_NAME, ExceptionConstants.ACCOUNT_NOT_EXISTED);
    }
}
