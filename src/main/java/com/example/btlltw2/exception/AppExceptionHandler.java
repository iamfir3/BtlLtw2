package com.example.btlltw2.exception;


import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value={ApiException.class})
    public ResponseEntity<Object> handleUserServiceException(ApiException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), ex.getHttpStatus());
    }

        private record ErrorMessage(String message) {

    }
}

