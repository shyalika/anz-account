package com.anz.shyalika.account.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object class for the exceptions
 * 
 * @author Shyalika Benthotage
 */
@Getter
@Setter
public class ErrorResponse {

    private int error;

    private String message;

    public ErrorResponse(int error, String message) {
        this.error = error;
        this.message = message;
    }

}
