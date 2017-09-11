package com.bottle.team.common.reflection;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 15:14
 */
@Component
public class ReflectionFacadeImpl implements ReflectionFacade {
    @Override
    public <C, A extends Annotation> boolean hasAnnotation(Class<C> c, Class<A> annotation) {
        return c.isAnnotationPresent(annotation);
    }

    @Override
    public <C, A extends Annotation> A getAnnotation(Class<C> c, Class<A> annotation) {
        return c.getAnnotation(annotation);
    }
}
