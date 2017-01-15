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
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface IndexedEmbedded {
    Boost boost() default @Boost(0.5F);
}
