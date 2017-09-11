package com.bottle.team.common.reflection;

import java.lang.annotation.Annotation;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 15:04
 */
public interface ReflectionFacade {
    <C, A extends Annotation> boolean hasAnnotation(Class<C> c, Class<A> annotation);

    <C, A extends Annotation> A getAnnotation(Class<C> c, Class<A> annotation);
}
