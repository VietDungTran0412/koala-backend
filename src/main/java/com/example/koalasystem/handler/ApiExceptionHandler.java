package com.example.koalasystem.handler;

import com.example.koalasystem.dto.RestResponseDto;
import com.example.koalasystem.exception.EntityNotFoundException;
import com.example.koalasystem.exception.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* Handle API Exception */
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponseDto handleUsernameNotFound(EntityNotFoundException ex) {
        return RestResponseDto.builder().message("Username has not been found!").build();
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, IllegalArgumentException.class, PaymentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseDto handleUsernameNotFound(MethodArgumentNotValidException ex) {
        return RestResponseDto.builder().message(ex.getBody().getTitle() + ": " + ex.getBody().getDetail() ).build();
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponseDto handleCommonException(RuntimeException ex) {
        return RestResponseDto.builder().message(ex.getMessage()).build();
    }
}
