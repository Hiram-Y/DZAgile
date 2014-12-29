package com.decade.agile.validator;

public class DCValidatorException extends java.lang.Exception{
    private static final long serialVersionUID = 1L;

    public DCValidatorException() {
        super();
    }

    /**
     * @param detailMessage
     * @param throwable
     */
    public DCValidatorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * @param detailMessage
     */
    public DCValidatorException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param throwable
     */
    public DCValidatorException(Throwable throwable) {
        super(throwable);
    }
}
