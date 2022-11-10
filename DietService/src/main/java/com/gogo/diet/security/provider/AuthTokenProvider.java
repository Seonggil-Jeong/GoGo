package com.gogo.diet.security.provider;

import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

public interface AuthTokenProvider<T> {

    T convertAuthToken(String token);

    Authentication getAuthentication(T authToken);
}
