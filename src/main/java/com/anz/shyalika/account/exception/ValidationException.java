package com.anz.shyalika.account.exception;

/**
 * Exception class used for validation exceptions of the REST interface
 * 
 * @author Shyalika Benthotage
 */
public class ValidationException extends AccountException {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * {@link ValidationException} constructor
     * 
     * @param errorCode-
     *            specific error code
     * @param message
     *            - validation message
     */
    public ValidationException(int errorCode, String message) {
        super(errorCode, message);
    }

}
