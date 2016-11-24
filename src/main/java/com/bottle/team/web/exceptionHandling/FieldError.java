package com.bottle.team.web.exceptionHandling;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by AKuzmanoski on 23/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/11/2016
 */
public class FieldError {
    private String field;
    private String code;
    private String message;

    public FieldError(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static List<FieldError> getErrors(
            Set<ConstraintViolation<?>> constraintViolations) {

        return constraintViolations.stream()
                .map(FieldError::of).collect(Collectors.toList());
    }

    private static FieldError of(ConstraintViolation<?> constraintViolation) {

        String field = StringUtils.substringAfter(
                constraintViolation.getPropertyPath().toString(), ".");

        return new FieldError(field,
                constraintViolation.getMessageTemplate(),
                constraintViolation.getMessage());

    }
}
