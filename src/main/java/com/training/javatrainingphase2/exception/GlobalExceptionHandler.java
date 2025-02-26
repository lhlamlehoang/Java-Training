package com.training.javatrainingphase2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MenuItemNotFoundException.class})
    public ResponseEntity<Object> handleMenuItemNotFoundException(MenuItemNotFoundException menuItemNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(menuItemNotFoundException.getMessage());
    }

    @ExceptionHandler({BillItemNotFoundException.class})
    public ResponseEntity<Object> handleBillItemNotFoundException(BillItemNotFoundException billItemNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(billItemNotFoundException.getMessage());
    }

    @ExceptionHandler({BillNotFoundException.class})
    public ResponseEntity<Object> handleBillNotFoundException(BillNotFoundException billNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(billNotFoundException.getMessage());
    }

    @ExceptionHandler({UserUnauthorizedException.class})
    public ResponseEntity<Object> handleUserUnauthorizedException(UserUnauthorizedException userUnauthorizedException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(userUnauthorizedException.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
