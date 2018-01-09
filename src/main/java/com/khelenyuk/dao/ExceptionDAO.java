package com.khelenyuk.dao;

public class ExceptionDAO extends Exception {
    public ExceptionDAO() {
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDAO(Throwable cause) {
        super(cause);
    }

    public ExceptionDAO(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
