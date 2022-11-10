package com.gogo.user.service;

import com.gogo.user.vo.LoginUserRequest;
import com.gogo.user.vo.RegisterUserRequest;

public interface AuthService {

    String userLogin(final LoginUserRequest request) throws Exception;

    void userRegister(final RegisterUserRequest request) throws Exception;
}
