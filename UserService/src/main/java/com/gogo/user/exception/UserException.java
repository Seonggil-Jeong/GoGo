package com.gogo.user.exception;

import com.gogo.user.exception.result.UserExceptionResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserExceptionResult exceptionResult;
}
