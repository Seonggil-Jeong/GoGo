package com.gogo.user.app.service;

import com.gogo.user.app.vo.LoginUserRequest;
import com.gogo.user.app.vo.RegisterUserRequest;

public interface AuthService {

    String userLogin(final LoginUserRequest request) throws Exception;

    void userRegister(final RegisterUserRequest request) throws Exception;
}
