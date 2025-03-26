package com.dct.nextgen.dto.auth;

import org.springframework.security.core.Authentication;
import com.dct.nextgen.security.jwt.JwtProvider;

/**
 * User information after successful authentication, used to generate the access token
 * Used in {@link JwtProvider#createToken(BaseAuthTokenDTO)}
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public class BaseAuthTokenDTO {

    private Authentication authentication; // Contains user authorities information
    private String username;
    private String deviceID;
    private Integer userID;
    private Boolean isRememberMe = false;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BaseAuthTokenDTO instance = new BaseAuthTokenDTO();

        public Builder authentication(Authentication authentication) {
            instance.authentication = authentication;
            return this;
        }

        public Builder username(String username) {
            instance.username = username;
            return this;
        }

        public Builder deviceID(String deviceID) {
            instance.deviceID = deviceID;
            return this;
        }

        public Builder userID(Integer userID) {
            instance.userID = userID;
            return this;
        }

        public Builder rememberMe(boolean rememberMe) {
            instance.isRememberMe = rememberMe;
            return this;
        }

        public BaseAuthTokenDTO build() {
            return instance;
        }
    }

    public BaseAuthTokenDTO() {}

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Boolean isRememberMe() {
        return isRememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        isRememberMe = rememberMe;
    }
}
