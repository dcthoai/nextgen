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

    @Column(name = "fullname")
    private String fullname;

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

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "device_ID", length = 45, unique = true)
    private String deviceID;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Account instance = new Account();

        public Builder username(String username) {
            instance.username = username;
            return this;
        }

        public Builder fullname(String fullname) {
            instance.fullname = fullname;
            return this;
        }

        public Builder password(String password) {
            instance.password = password;
            return this;
        }

        public Builder email(String email) {
            instance.email = email;
            return this;
        }

        public Builder address(String address) {
            instance.address = address;
            return this;
        }

        public Builder phone(String phone) {
            instance.phone = phone;
            return this;
        }

        public Builder status(String status) {
            instance.status = status;
            return this;
        }

        public Builder deviceID(String deviceID) {
            instance.deviceID = deviceID;
            return this;
        }

        public Builder roles(List<Role> roles) {
            instance.roles = roles;
            return this;
        }

        public Account build() {
            return instance;
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
