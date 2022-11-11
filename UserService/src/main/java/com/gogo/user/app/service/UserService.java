package com.gogo.user.app.service;

import com.gogo.user.app.vo.UpdateUserRequest;
import com.gogo.user.app.vo.UserInfoResponse;

public interface UserService {
    UserInfoResponse findUserInfo(final String userId) throws Exception;

    UserInfoResponse updateUserInfo(final String userId, final UpdateUserRequest request) throws Exception;
}
