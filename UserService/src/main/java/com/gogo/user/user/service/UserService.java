package com.gogo.user.user.service;

import com.gogo.user.user.vo.UpdateUserRequest;
import com.gogo.user.user.vo.UserInfoResponse;

public interface UserService {
    UserInfoResponse findUserInfo(final String userId) throws Exception;

    UserInfoResponse updateUserInfo(final String userId, final UpdateUserRequest request) throws Exception;
}
