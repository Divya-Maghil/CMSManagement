package org.contentmanagement.digimenucms.error;

public class AwsImageUploadFailedException extends Exception{
    public AwsImageUploadFailedException() {
        super();
    }

    public AwsImageUploadFailedException(String message) {
        super(message);
    }

    public AwsImageUploadFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AwsImageUploadFailedException(Throwable cause) {
        super(cause);
    }

    protected AwsImageUploadFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
