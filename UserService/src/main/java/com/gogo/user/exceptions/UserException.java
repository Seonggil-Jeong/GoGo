package com.gogo.user.exceptions;

import com.gogo.user.exceptions.result.UserExceptionResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserExceptionResult exceptionResult;
}
