package com.example.hospital.Exception;


import com.example.hospital.Exception.CustomException.AccountExists;
import com.example.hospital.Exception.CustomException.DataNotFoundException;
import com.example.hospital.Exception.CustomException.DuplicatedUsername;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AccountExists.class)
    public ResponseEntity<Object> AccountExistsHandler(
            AccountExists ex, WebRequest request ) {


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Số điện thoại hoặc email này đã được đăng kí trước đó");
        errorResponse.setDetails(details);
        return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
    }

    @ExceptionHandler(DuplicatedUsername.class)
    public ResponseEntity<Object> DuplicatedUsernameHandler(
            AccountExists ex, WebRequest request ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("username này đã được đăng kí");
        errorResponse.setDetails(details);
        return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> DataNotFoundExceptionHandler(
            AccountExists ex, WebRequest request ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("username hoặc password bị sai");
        errorResponse.setDetails(details);
        return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
    }
}
