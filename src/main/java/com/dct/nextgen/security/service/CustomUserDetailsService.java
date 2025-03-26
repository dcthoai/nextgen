package com.dct.nextgen.security.service;

import com.dct.nextgen.constants.ExceptionConstants;
import com.dct.nextgen.dto.mapping.IPermissionDTO;
import com.dct.nextgen.entity.base.Account;
import com.dct.nextgen.repositories.common.AccountRepository;
import com.dct.nextgen.repositories.common.PermissionRepository;
import com.dct.nextgen.security.model.CustomUserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final AccountRepository accountRepository;
    private final PermissionRepository permissionRepository;

    public CustomUserDetailsService(AccountRepository accountRepository,
                                    PermissionRepository permissionRepository) {
        this.accountRepository = accountRepository;
        this.permissionRepository = permissionRepository;
        log.debug("UserDetailsService 'CustomUserDetailsService' is configured for load user credentials info");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by username: " + username);
        Optional<Account> accountOptional = accountRepository.findByUsername(username);

        if (accountOptional.isEmpty())
            throw new UsernameNotFoundException(ExceptionConstants.ACCOUNT_NOT_FOUND);

        Account account = accountOptional.get();
        List<IPermissionDTO> userPermissions = permissionRepository.findAllByAccountID(account.getId());

        Collection<SimpleGrantedAuthority> userAuthorities = userPermissions
            .stream()
            .filter(permissionDTO -> StringUtils.hasText(permissionDTO.getCode()))
            .map(permissionDTO -> new SimpleGrantedAuthority(permissionDTO.getCode()))
            .collect(Collectors.toSet());

        return new CustomUserDetails(account, userAuthorities);
    }
}
