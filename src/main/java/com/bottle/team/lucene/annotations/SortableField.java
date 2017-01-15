package com.bottle.team.lucene.annotations;

import java.lang.annotation.*;

/**
 * Created by AKuzmanoski on 12/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/01/2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface SortableField {
}
