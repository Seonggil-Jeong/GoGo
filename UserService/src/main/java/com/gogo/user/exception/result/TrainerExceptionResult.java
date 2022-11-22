package com.gogo.user.exception.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TrainerExceptionResult {
    TRAINER_ID_CAN_NOT_USE(HttpStatus.NOT_FOUND, "해당 트레이너를 찾을 수 없습니다"),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
