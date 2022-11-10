package com.gogo.user.user.service;

import com.gogo.user.user.vo.LoginUserRequest;
import com.gogo.user.user.vo.RegisterUserRequest;

public interface AuthService {

    String userLogin(final LoginUserRequest request) throws Exception;

    void userRegister(final RegisterUserRequest request) throws Exception;
}
