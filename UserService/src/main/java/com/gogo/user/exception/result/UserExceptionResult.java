package com.gogo.user.exception.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionResult {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 사용자 정보를 찾을 수 없습니다"),
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "비밀번호를 확인해주세요"),
    EXPIRED_JWT_TOKEN(HttpStatus.FORBIDDEN, "만료된 토큰입니다"),
    DUPLICATED_USER_ID(HttpStatus.BAD_REQUEST, "중복된 사용자 아이디입니다"),
    DUPLICATED_USER_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 사용자 닉네임입니다"),
    TRAINER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 트레이너 정보를 찾을 수 없습니다"),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
