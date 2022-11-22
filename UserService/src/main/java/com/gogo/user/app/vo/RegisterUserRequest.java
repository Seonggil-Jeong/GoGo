package com.gogo.user.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(name = "사용자 회원가입 Request")
public class RegisterUserRequest {
    @Schema(description = "사용자 아이디", example = "userId", required = true)
    @NotNull(message = "user Id cannot be null")
    private final String userId;

    @Schema(description = "사용자 비밀번호", example = "userPassword", required = true)
    @NotNull(message = "user password cannot be null")
    private final String userPassword;

    @Schema(description = "사용자 이름", example = "userName", required = true)
    @NotNull(message = "user Name cannot be null")
    private final String userName;

    @Schema(description = "사용자 닉네임", example = "userNickname", required = true)
    @NotNull(message = "user nickname cannot be null")
    private final String userNickname;

    @Schema(description = "트레이너 아이디", example = "userId", required = false)
    private final String trainerId;
}
