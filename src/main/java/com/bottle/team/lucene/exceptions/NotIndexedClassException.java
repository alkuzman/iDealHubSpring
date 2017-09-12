package com.bottle.team.lucene.exceptions;

/**
 * Created by AKuzmanoski on 12/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/09/2017 18:30
 */
public class NotIndexedClassException extends Exception {
    Class<?> c;

    public NotIndexedClassException(String message, Class<?> c) {
        super(message + ". The field " + c.getName() + " is not indexed.");
        this.c = c;
    }

    public NotIndexedClassException(Class<?> c) {
        super("The class " + c.getName() + " is not indexed.");
        this.c = c;
    }

    public NotIndexedClassException(String message, Throwable cause, Class<?> c) {
        super(message + ". The field " + c.getName() + " is not indexed.", cause);
        this.c = c;
    }

    public NotIndexedClassException(Throwable cause, Class<?> c) {
        super("The field " + c.getName() + " is not indexed.", cause);
        this.c = c;
    }

    protected NotIndexedClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Class<?> c) {
        super(message + ". The field " + c.getName() + " is not indexed.", cause, enableSuppression, writableStackTrace);
        this.c = c;
    }

    public Class<?> getNotIndexedClass() {
        return c;
    }
}