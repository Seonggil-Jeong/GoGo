package com.gogo.user.security.filter;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.TokenType;
import com.gogo.user.exceptions.UserException;
import com.gogo.user.exceptions.result.UserExceptionResult;
import com.gogo.user.security.impl.AccessTokenProvider;
import com.gogo.user.security.token.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.login.AccountException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.gogo.user.constants.UserConstants.AUTHORIZATION_TOKEN_KEY;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final AccessTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional<String> token = resolveToken(request);

        if (token.isPresent()) {
            HttpSession session = request.getSession();


            if (session.getAttribute(token.get()) != null) {
                throw new UserException(UserExceptionResult.EXPIRED_JWT_TOKEN);
            }

            AccessToken accessToken = tokenProvider.convertAuthToken(token.get());


            if (accessToken.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response);

    }


    private Optional<String> resolveToken(HttpServletRequest request) {
        String authToken = request.getHeader(AUTHORIZATION_TOKEN_KEY);

        if (StringUtils.hasText(authToken)) {
            return Optional.of(authToken);
        } else {
            return Optional.empty();
        }
    }

}
