package com.gogo.user.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(name = "로그인 Request")
public class LoginUserRequest {
    @Schema(description = "사용자 아이디", example = "userId", required = true)
    @NotNull(message = "user Id cannot be null")
    private final String userId;

    @Schema(description = "사용자 비밀번호", example = "userPassword", required = true)
    @NotNull(message = "userPassword cannot be null")
    private final String userPassword;
}
