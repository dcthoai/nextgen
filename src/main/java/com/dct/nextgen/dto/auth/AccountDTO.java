package com.dct.nextgen.dto.auth;

import com.dct.nextgen.dto.mapping.IRoleDTO;
import com.dct.nextgen.dto.response.AuditingEntityDTO;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AccountDTO extends AuditingEntityDTO {

    private Integer id;
    private String fullname;
    private String username;
    private String email;
    private String status;
    private List<IRoleDTO> accountRoles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<IRoleDTO> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<IRoleDTO> accountRoles) {
        this.accountRoles = accountRoles;
    }
}
