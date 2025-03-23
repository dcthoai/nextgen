package com.dct.nextgen.security.jwt;

import com.dct.nextgen.common.BaseCommon;
import com.dct.nextgen.common.JsonUtils;
import com.dct.nextgen.constants.HttpStatusConstants;
import com.dct.nextgen.constants.SecurityConstants;
import com.dct.nextgen.dto.response.BaseResponseDTO;
import com.dct.nextgen.exception.BaseAuthenticationException;
import com.dct.nextgen.constants.SecurityConstants.REQUEST_MATCHERS;
import com.dct.nextgen.exception.BaseBadRequestException;
import com.dct.nextgen.exception.BaseException;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is found
 * @author thoaidc
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    private static final String ENTITY_NAME = "JwtTokenFilter";
    private final JwtProvider jwtProvider;
    private final BaseCommon baseCommon;

    public JwtFilter(JwtProvider jwtProvider, BaseCommon baseCommon) {
        this.jwtProvider = jwtProvider;
        this.baseCommon = baseCommon;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        if (ifAuthenticationRequired(request)) {
            try {
                String token = resolveToken(request);
                Authentication authentication = this.jwtProvider.validateToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BaseBadRequestException | BaseAuthenticationException exception) {
                handleException(response, exception);
                return;
            } catch (Exception exception) {
                log.error("JwtFilter unable to process response for BaseAuthenticationException", exception);
                throw exception;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean ifAuthenticationRequired(HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();
        log.info("Filtering {}: {}", request.getMethod(), requestURI);

        return Arrays.stream(REQUEST_MATCHERS.PUBLIC).noneMatch(pattern -> antPathMatcher.match(pattern, requestURI));
    }

    private String resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String bearerToken = null;

        if (Objects.nonNull(cookies)) {
            bearerToken = Arrays.stream(cookies)
                    .filter(cookie -> SecurityConstants.COOKIES.HTTP_ONLY_COOKIE_ACCESS_TOKEN.equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        if (!StringUtils.hasText(bearerToken))
            bearerToken = request.getHeader(SecurityConstants.HEADER.AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(bearerToken))
            bearerToken = request.getHeader(SecurityConstants.HEADER.AUTHORIZATION_GATEWAY_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.HEADER.TOKEN_TYPE))
            return bearerToken.substring(7);

        return bearerToken;
    }

    private void handleException(HttpServletResponse response, BaseException exception) throws IOException {
        log.error("[{}] - Handling exception {}", ENTITY_NAME, exception.getClass().getName(), exception);
        response.setStatus(HttpStatusConstants.UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponseDTO responseDTO = BaseResponseDTO.builder()
            .code(HttpStatusConstants.UNAUTHORIZED)
            .success(HttpStatusConstants.STATUS.FAILED)
            .message(baseCommon.getMessageI18n(exception.getErrorKey(), exception.getArgs()))
            .build();

        response.getWriter().write(JsonUtils.toJsonString(responseDTO));
        response.flushBuffer();
    }
}
