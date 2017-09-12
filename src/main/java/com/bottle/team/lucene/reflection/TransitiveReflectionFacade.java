package com.bottle.team.lucene.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 15:05
 */
@Primary
@Component
public class TransitiveReflectionFacade extends ReflectionFacadeDecorator {

    @Autowired
    public TransitiveReflectionFacade(@Qualifier("reflectionFacadeImpl") ReflectionFacade decoratee) {
        super(decoratee);
    }

    @Override
    public <C, A extends Annotation> boolean hasAnnotation(Class<C> c, Class<A> annotation) {
        return getAnnotation(c, annotation) != null;
    }

    @Override
    public <C, A extends Annotation> A getAnnotation(Class<C> c, Class<A> annotation) {
        if (c == null) {
            return null;
        }
        A a = getDecoratee().getAnnotation(c, annotation);
        if (a != null) {
            return a;
        }
        a = getAnnotation(c.getSuperclass(), annotation);
        if (a != null) {
            return a;
        }
        for (Class<?> i : c.getInterfaces()) {
            a = getAnnotation(i, annotation);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    @Override
    public <C, A extends Annotation> boolean hasAnnotation(Field field, Class<A> annotation) {
        return getDecoratee().hasAnnotation(field, annotation);
    }

    @Override
    public <C, A extends Annotation> A getAnnotation(Field field, Class<A> annotation) {
        return getDecoratee().getAnnotation(field, annotation);
    }
}
