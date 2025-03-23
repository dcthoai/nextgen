package com.dct.nextgen.entity.base;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information about users in the system <p>
 * Including those authenticated and authorized through third-party services like Google, Facebook
 *
 * @author thoaidc
 */
@Entity
@DynamicUpdate // Hibernate only updates the changed columns to the database instead of updating the entire table
@Table(name = "account")
@SuppressWarnings("unused")
public class Account extends AbstractAuditingEntity {

    @Column(name = "username", length = 45, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "address", length = 512)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "device_ID", length = 45, unique = true)
    private String deviceID;

    @Column(name = "token", length = 512)
    private String token;

    @ManyToMany(
        cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH },
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "account_role",
        joinColumns = @JoinColumn(name = "account_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "role_ID", referencedColumnName = "ID")
    )
    private List<Role> roles = new ArrayList<>();

    public Account() {}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
