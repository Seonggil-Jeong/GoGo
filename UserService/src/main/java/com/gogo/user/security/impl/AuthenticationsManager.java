package com.gogo.user.security.impl;

import com.gogo.user.exceptions.UserException;
import com.gogo.user.exceptions.result.UserExceptionResult;
import com.gogo.user.repository.UserRepository;
import com.gogo.user.repository.entity.UserEntity;
import com.gogo.user.security.token.PasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AuthenticationsManager implements AuthenticationProvider {
    private final UserRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserEntity user = accountRepository.findById(authentication.getPrincipal().toString()).orElseThrow(()
                -> new UserException(UserExceptionResult.USER_NOT_FOUND));


        if (user.getUserState() != 1) {
            throw new UserException(UserExceptionResult.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getUserPassword())) {
            throw new UserException(UserExceptionResult.PASSWORD_NOT_MATCHED);
        }
        PasswordAuthenticationToken token = new PasswordAuthenticationToken(user.getUserId(), user.getUserPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getValue())));

        token.setRole(user.getRole().getValue());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PasswordAuthenticationToken.class);
    }
}
