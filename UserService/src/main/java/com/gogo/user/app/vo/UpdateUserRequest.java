package com.gogo.user.app.vo;

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
@Schema(name = "사용자 정보 변경 Request")
public class UpdateUserRequest {

    @Schema(description = "사용자 이름", example = "userName", required = true)
    @NotNull(message = "user Name cannot be null")
    private final String userName;

    @Schema(description = "사용자 닉네임", example = "userNickname", required = true)
    @NotNull(message = "user nickname cannot be null")
    private final String userNickname;

    @Schema(description = "트레이너 아이디", example = "userId", required = false)
    private final String trainerId;
}
