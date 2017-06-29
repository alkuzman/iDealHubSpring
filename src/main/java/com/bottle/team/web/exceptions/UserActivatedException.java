package com.bottle.team.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Viki on 6/15/2017.
 */
@ResponseStatus(value = HttpStatus.GONE)
public class UserActivatedException extends RuntimeException {
    public UserActivatedException(String s) {
        super(s);
    }
}
