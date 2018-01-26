package com.khelenyuk.dao;

public class ExceptionDao extends Exception {
    public ExceptionDao() {
    }

    public ExceptionDao(String message) {
        super(message);
    }

    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDao(Throwable cause) {
        super(cause);
    }

    public ExceptionDao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
