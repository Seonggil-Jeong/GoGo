package com.gogo.user.user.service;

public interface VarifyUserService {

    String getValidateUserId(final String userId) throws Exception;

    String getValidateTrainerId(final String trainerId) throws Exception;

    String getValidateUserNickname(final String userNickname) throws Exception;
}
