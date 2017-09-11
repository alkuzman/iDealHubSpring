package com.bottle.team.lucene.annotations;

import java.lang.annotation.*;

/**
 * Created by AKuzmanoski on 11/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 11/01/2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Indexed {
    String index() default "";
}
