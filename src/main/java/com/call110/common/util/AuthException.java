package com.call110.common.util;

public class AuthException extends RuntimeException{

	private static final long serialVersionUID = 5996981330707737683L;
	public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
