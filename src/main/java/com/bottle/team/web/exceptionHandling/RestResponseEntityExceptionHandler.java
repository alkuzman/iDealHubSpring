package com.bottle.team.web.exceptionHandling;

import com.bottle.team.web.exceptions.RegistrationUnsuccessfulException;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import com.bottle.team.web.exceptions.UserNotActivatedException;
import com.fasterxml.jackson.core.ObjectCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by AKuzmanoski on 23/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/11/2016
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public RestResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", "ConstraintViolationException");
        map.put("errors", processFieldErrors(fieldErrors));
        return map;
    }

    @ExceptionHandler(UserNotActivatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String userNotActivated(UserNotActivatedException ex) {
        return ex.getMessage();
    }

    private List<FieldError> processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        List<FieldError> errors = new ArrayList<FieldError>();
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            errors.add(processFieldError(fieldError));
        }

        return errors;
    }

    private FieldError processFieldError(org.springframework.validation.FieldError fieldError) {
        return new FieldError(fieldError.getField(), fieldError.getCode(), resolveLocalizedErrorMessage(fieldError));
    }

    private String resolveLocalizedErrorMessage(org.springframework.validation.FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        /*System.out.println(localizedErrorMessage);
        System.out.println(fieldError.getDefaultMessage());
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }*/

        return localizedErrorMessage;
    }
}