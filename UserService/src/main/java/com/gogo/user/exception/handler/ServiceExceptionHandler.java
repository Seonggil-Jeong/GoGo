package com.gogo.user.exception.handler;

import com.gogo.user.exception.TrainerException;
import com.gogo.user.exception.UserException;
import com.gogo.user.exception.result.TrainerExceptionResult;
import com.gogo.user.exception.result.UserExceptionResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        log.error("Exception occur: ", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("UNKNOWN_EXCEPTION ", "Unknown Exception"));
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorResponse> handleUserException(final UserException exception) {
        log.error("UserException occur : ", exception);

        return makeErrorResponseEntity(exception.getExceptionResult());
    }

    @ExceptionHandler({TrainerException.class})
    public ResponseEntity<ErrorResponse> handleTrainerException(final TrainerException exception) {
        log.error("TrainerException occur : ", exception);

        return makeErrorResponseEntity(exception.getExceptionResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("Exception occur: ", exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("VALIDATION_FAILED", exception.getAllErrors().get(0).getDefaultMessage()));
    }




    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(final UserExceptionResult exceptionResult) {
        return ResponseEntity.status(exceptionResult.getHttpStatus())
                .body(new ErrorResponse(exceptionResult.name(), exceptionResult.getMessage()));
    }

    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(final TrainerExceptionResult exceptionResult) {
        return ResponseEntity.status(exceptionResult.getHttpStatus())
                .body(new ErrorResponse(exceptionResult.name(), exceptionResult.getMessage()));
    }


    @Getter
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final String code;
        private final String message;
        private final Date createAt = new Date();
    }
}
