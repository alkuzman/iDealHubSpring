package com.bottle.team.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Viki on 6/15/2017.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ActivationCodeIsWrongException extends RuntimeException {
    public ActivationCodeIsWrongException(String s) {
        super(s);
    }
}
