package com.anz.shyalika.account.exception;

/**
 * Exception class used for account services specific exceptions
 * 
 * @author Shyalika Benthotage
 */
public class AccountException extends RuntimeException {

    /**
     * Serial version Uid
     */
    private static final long serialVersionUID = 1L;

    private int errorCode;

    /**
     * {@link AccountException} constructor
     * 
     * @param errorCode
     *            - specific error code for the exception
     * @param message
     *            - error message
     * @param e
     *            exception instance
     */
    public AccountException(int errorCode, String message, Throwable e) {
        super(message, e);
    }

    /**
     * {@link AccountException} constructor
     * 
     * @param errorCode
     *            - specific error code for the exception
     * @param message
     *            - error message
     */
    public AccountException(int errorCode, String message) {
        super(message);
    }

    /**
     * Returns the error code of the exception
     * 
     * @return
     */
    public int getErrorCode() {
        return this.errorCode;
    }

}
