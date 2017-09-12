package com.bottle.team.lucene.config.model.customAnimations;

import com.bottle.team.lucene.annotations.Indexed;
import com.bottle.team.lucene.config.model.ClassConfig;

/**
 * Created by AKuzmanoski on 12/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/09/2017 18:17
 */
public class CustomAnnotationsClassConfigAdapter implements ClassConfig {
    private final Indexed indexed;
    private final Class<?> c;

    public CustomAnnotationsClassConfigAdapter(Indexed indexed, Class<?> c) {
        this.indexed = indexed;
        this.c = c;
    }
}
