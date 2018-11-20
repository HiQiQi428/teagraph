package org.luncert.tkglb.cypher;

public class AstError extends RuntimeException {

    private static final long serialVersionUID = 1805776627159910327L;
    public AstError() {
        super();
    }

    public AstError(String message) {
        super(message);
    }

    public AstError(String message, Throwable cause) {
        super(message, cause);
    }

    public AstError(Throwable cause) {
        super(cause);
    }

    public AstError(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}