package com.bottle.team.lucene.exceptions;

import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 12/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/09/2017 18:35
 */
public class NotIndexedFieldException extends Exception {
    Field field;

    public NotIndexedFieldException(String message, Field field) {
        super(message + ". The field " + field.getName() + " from " + field.getDeclaringClass().getName() + " is not indexed.");
        this.field = field;
    }

    public NotIndexedFieldException(Field field) {
        super("The field " + field.getName() + " from " + field.getDeclaringClass().getName() + " is not indexed.");
        this.field = field;
    }

    public NotIndexedFieldException(String message, Throwable cause, Field field) {
        super(message + ". The field " + field.getName() + " from " + field.getDeclaringClass().getName() + " is not indexed.", cause);
        this.field = field;
    }

    public NotIndexedFieldException(Throwable cause, Field field) {
        super("The field " + field.getName() + " from " + field.getDeclaringClass().getName() + " is not indexed.", cause);
        this.field = field;
    }

    protected NotIndexedFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Field field) {
        super(message + ". The field " + field.getName() + " from " + field.getDeclaringClass().getName() + " is not indexed.", cause, enableSuppression, writableStackTrace);
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}