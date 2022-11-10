package com.gogo.user.security.impl;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.TokenType;
import com.gogo.user.security.AuthToken;
import com.gogo.user.security.AuthTokenProvider;
import com.gogo.user.security.token.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class AccessTokenProvider implements AuthTokenProvider<AccessToken> {
    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public AccessToken createToken(String id, String role, Map<String, Object> claims, Date expiredDate) {
        return new AccessToken(id, key, role, claims, expiredDate);
    }

    @Override
    public AccessToken convertAuthToken(String token) {
        return new AccessToken(token, key);
    }

    @Override
    public Authentication getAuthentication(AccessToken authToken) {
        if (authToken.validate()) {
            Claims claims = authToken.getData();
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                    new SimpleGrantedAuthority(claims.get(AuthToken.AUTHORITIES_KEY, String.class)));

            User principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        } else {
            throw new JwtException("token Error");
        }
    }

    public String getUserId(final String token) {
        final AccessToken jwtAuthToken = this.convertAuthToken(token);

        return jwtAuthToken.getData().getSubject();
    }

    public String getType(final String token) {
        final AccessToken jwtAuthToken = this.convertAuthToken(token);

        return String.valueOf(jwtAuthToken.getData().get("type"));
    }
}
