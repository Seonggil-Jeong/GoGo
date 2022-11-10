package com.gogo.user.security;

public interface AuthToken<T> {
    final static String AUTHORITIES_KEY = "role";
    final static String TOKEN_TYPE_KEY = "type";

    boolean validate();

    T getData();
}
