package com.anz.shyalika.account.exception.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;
import org.springframework.web.server.MethodNotAllowedException;

import com.anz.shyalika.account.exception.AccountException;
import com.anz.shyalika.account.exception.ValidationException;
import com.anz.shyalika.account.model.ErrorResponse;

/**
 * An exception mapper to be used as a RestControllerAdvice so that the object returned is
 * automatically serialized into JSON and passed it to the HttpResponse object
 * 
 * @author Shyalika Benthotage
 */
@RestControllerAdvice
public class AccountExceptionMapper {

    /**
     * Handles {@link ValidationException} errors
     * 
     * @param ex
     *            validation exception
     * @return a response entity to be returned to the user
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> toResponse(ValidationException ex) {
        ErrorResponse response = new ErrorResponse(1000, ex.getMessage());
        return ResponseEntity.status(422).body(response);
    }

    /**
     * Handles {@link AccountException} errors
     * 
     * @param ex
     *            general account exception
     * @return a response entity to be returned to the user
     */
    @ExceptionHandler(AccountException.class)
    protected ResponseEntity<Object> toResponse(AccountException ex) {
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Default exception handler
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> toResponse(Exception ex) {
        ErrorResponse response = new ErrorResponse(2000, "An internal error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    /**
     * Handles {@link HttpRequestMethodNotSupportedException} errors
     * 
     * @param ex
     *            exception
     * @return a message to be returned to the user
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> toNotSupportedResponse(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(405).body("Method not supported");
    }
    

    /**
     * Handles {@link MethodNotAllowedException} errors
     * 
     * @param ex
     *            exception
     * @return a message to be returned to the user
     */
    @ExceptionHandler(MethodNotAllowedException.class)
    protected ResponseEntity<Object> toNotAllowedResponse(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(405).body("Method not allowed");
    }
    
    /**
     * Handles {@link MethodArgumentTypeMismatchException} errors
     * 
     * @param ex
     *            exception
     * @return a message to be returned to the user
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> toNotAllowedResponse(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(405).body("Method argument type mismatched");
    }

    /**
     * Handles {@link HttpMediaTypeNotSupportedException} errors
     * 
     * @param ex
     *            exception
     * @return a message to be returned to the user
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<Object> toMediaTypeNorSupportedResponse(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity.status(500).body("Content-Type application/json is missing");
    }

}
