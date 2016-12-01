package org.turkisi.barinakta.api.error;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ApiException extends Exception {


    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
