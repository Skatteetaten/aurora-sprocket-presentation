package com.example.demoweb;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map> illegalArgument(final IllegalArgumentException e) {

        return new ResponseEntity<>(new HashMap<String, String>() {{
            put("error", e.getMessage());
        }}, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> response = new HashMap<String, String>() {{
            put("error", e.getMessage());
        }};
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, response, headers, HttpStatus.BAD_REQUEST, request);
    }
}
