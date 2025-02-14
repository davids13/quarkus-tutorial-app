package com.redhat.developers.resterrors;

public class InfoNotFoundException extends RuntimeException {

    public InfoNotFoundException() {
    }

    public InfoNotFoundException(String message) {
        super(message);
    }

    public InfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoNotFoundException(Throwable cause) {
        super(cause);
    }

    public InfoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
