package org.contentmanagement.digimenucms.error;

public class ImageNotFoundException extends Exception{

    public ImageNotFoundException() {
        super();
    }

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ImageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
