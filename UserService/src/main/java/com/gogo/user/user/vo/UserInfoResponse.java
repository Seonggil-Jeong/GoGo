package com.gogo.user.user.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserInfoResponse {
    private final String userId;
    private final String userName;
    private final String userNickname;
    private final String trainerId;

}
