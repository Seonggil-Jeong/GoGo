package com.gogo.user.exception;

import com.gogo.user.exception.result.TrainerExceptionResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrainerException extends RuntimeException{


    private final TrainerExceptionResult exceptionResult;
}
