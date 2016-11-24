package com.bottle.team.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AKuzmanoski on 23/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/11/2016
*/
public class RegistrationUnsuccessfulException extends RuntimeException {
    public RegistrationUnsuccessfulException(String message) {
        super(message);
    }

    public RegistrationUnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationUnsuccessfulException(Throwable cause) {
        super(cause);
    }

    public RegistrationUnsuccessfulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RegistrationUnsuccessfulException() {
    }
}
