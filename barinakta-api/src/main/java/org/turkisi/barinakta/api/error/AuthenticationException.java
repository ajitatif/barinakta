package org.turkisi.barinakta.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid user name password combination")
public class AuthenticationException extends ApiException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
