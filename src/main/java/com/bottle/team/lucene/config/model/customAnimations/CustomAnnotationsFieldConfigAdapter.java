package com.bottle.team.lucene.config.model.customAnimations;

import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.config.model.FieldConfig;

/**
 * Created by AKuzmanoski on 12/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/09/2017 18:29
 */
public class CustomAnnotationsFieldConfigAdapter implements FieldConfig {
    private final Field fieldAnnotation;
    private final java.lang.reflect.Field field;

    public CustomAnnotationsFieldConfigAdapter(Field fieldAnnotation, java.lang.reflect.Field field) {
        this.field = field;
        this.fieldAnnotation = fieldAnnotation;
    }
}
