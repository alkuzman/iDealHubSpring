package com.bottle.team.lucene.config.model;

import com.bottle.team.common.reflection.ReflectionFacade;
import com.bottle.team.lucene.annotations.Indexed;
import org.springframework.stereotype.Component;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 10:01
 */
@Component
public class AnnotationsModelConfig implements ModelConfig {
    private final ReflectionFacade reflection;

    public AnnotationsModelConfig(ReflectionFacade reflection) {
        this.reflection = reflection;
    }

    @Override
    public <T> boolean isIndexed(Class<T> c) {
        return reflection.hasAnnotation(c, Indexed.class);
    }
}
