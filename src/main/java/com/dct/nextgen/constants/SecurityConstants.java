package com.dct.nextgen.constants;

import com.dct.nextgen.config.InterceptorConfig;
import com.dct.nextgen.dto.BaseAuthTokenDTO;
import com.dct.nextgen.security.jwt.JwtProvider;
import com.dct.nextgen.security.config.SecurityConfig;
import com.dct.nextgen.aop.CheckAuthorize;

/**
 * Security configuration parameters
 * @author thoaidc
 */
@SuppressWarnings("unused")
public interface SecurityConstants {

    // The encryption complexity in PasswordEncoder's algorithm (between 4 and 31)
    // Higher values mean the password is harder to attack, but too high will reduce performance
    int BCRYPT_COST_FACTOR = 12;

    /**
     * The corresponding keys to store information in the payload of a JWT token <p>
     * See {@link JwtProvider#createToken(BaseAuthTokenDTO)} for details
     */
    interface TOKEN_PAYLOAD {
        String USER_ID = "userID";
        String USERNAME = "username";
        String DEVICE_ID = "deviceID";
        String AUTHORITIES = "auth";
    }

    /**
     * The paths for security configuration in {@link SecurityConfig#securityFilterChain} <p>
     * Requests matching the patterns below will have their own specific security rules applied <p>
     * Requests not listed will require authentication by default
     */
    interface REQUEST_MATCHERS {

        String[] ADMIN = { "/api/admin**", "/admin**" };
        String[] USER = { "/api/users**", "/users**" };
        String[] PUBLIC = {
            "/",
            "/**.html",
            "/**.css",
            "/**.js",
            "/**.ico",
            "/i18n/**",
            "/content/**",
            "/static/**",
            "/register",
            "/login",
            "/p/**",
            "/api/p/**"
        };
    }

    /**
     * The configurations applied in the CORS filter in {@link InterceptorConfig#corsFilter()}
     */
    interface CORS {
        String APPLY_FOR = "/**"; // CORS filter is applied to all requests
        String[] ALLOWED_HEADERS = {
            "Content-Type",     // Content format
            "Authorization",    // Authentication token
            "Accept",           // Client-expected content
            "Origin",           // Origin of the request
            "X-CSRF-Token",     // Anti-CSRF token
            "X-Requested-With", // Ajax request markup
            "Access-Control-Allow-Origin", // Server response header
            "X-App-Version",    // Application version (optional)
            "X-Device-ID"       // Device ID (optional)
        };

        String[] ALLOWED_REQUEST_METHODS = {"GET", "PUT", "POST", "DELETE"};
        String[] ALLOWED_ORIGIN_PATTERNS = {"*"}; // The list of domains allowed to access the resources. * means all
        boolean ALLOW_CREDENTIALS = true; // Allow sending cookies or authentication information
    }

    interface COOKIES {
        // The key of the cookie storing the JWT token, which is HTTP-only
        // This cookie is automatically sent with requests by browser but cannot be accessed by JavaScript
        String HTTP_ONLY_COOKIE_ACCESS_TOKEN = "dct_access_token";
    }

    interface HEADER {

        // The request header storing the JWT token, used in cases where the token is not found in the HTTP-only cookies
        String AUTHORIZATION_HEADER = "Authorization";
        String AUTHORIZATION_GATEWAY_HEADER = "Authorization-Gateway";
        String TOKEN_TYPE = "Bearer "; // JWT token type

        // Configure Content-Security-Policy (CSP), which controls how resources are loaded and executed on the website
        // The main goal is to minimize the risk of attacks such as Cross-Site Scripting (XSS) or Code Injection
        String SECURITY_POLICY = "default-src 'self';" + // Only allow resources to be loaded from the same domain
                // Only allow embedded content (iframes) from the same domain (self) or from URLs with schema data
                " frame-src 'self' data:;" +
                // Only allow script execution from the same domain (self), unsafe-inline (allows inline scripts),
                // unsafe-eval (allows eval()), and from the domain https://storage.googleapis.com
                " script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com;" +
                // Only allow styles from the same domain (self) and allow inline CSS (unsafe-inline)
                " style-src 'self' 'unsafe-inline';" +
                // Only allow images from the same domain (self) and from URLs with schema data
                " img-src 'self' data:;" +
                // Only allow fonts from the same domain (self) and from URLs with schema data
                " font-src 'self' data:";

        // Permissions-Policy limits access to sensitive browser features, helping protect user privacy
        String PERMISSIONS_POLICY = "camera=(), " + // Prevent any origin from using the camera
                "fullscreen=(self), " + // Only allow current domain (self) to use full screen mode
                "geolocation=(), " + // Prevent access to GPS location services
                "gyroscope=(), " + // Prevent use of gyroscope sensor
                "magnetometer=(), " + // Prevent use of magnetometer sensor
                "microphone=(), " + // Prevent microphone access
                "midi=(), " + // Prevent access to MIDI (digital musical instruments)
                "payment=(), " + // Prevent use of payment features
                "sync-xhr=()"; // Prevent use of synchronous XMLHttpRequest requests
    }

    /**
     * The list of ROLES used in this application <p>
     * Constants starting with the "ROLE" prefix are used for storing in the database or with @{@link CheckAuthorize}<p>
     * Constants without the "ROLE" prefix are used for {@link SecurityConfig#securityFilterChain} <p>
     * Spring Security automatically adds the "ROLE" prefix by default
     */
    interface ROLES {
        String ADMIN = "ADMIN";
        String USER = "USER";
        String ROLE_ADMIN = "ROLE_ADMIN";
        String ROLE_USER = "ROLE_USER";
    }
}
