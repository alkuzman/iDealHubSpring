package com.bottle.team.lucene.config.model.customAnimations;

import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.lucene.config.model.FieldConfig;

import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 12/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 12/09/2017 19:10
 */
public class CustomAnnotationsEmbeddedFieldConfigAdapter implements FieldConfig {
    private final Field field;
    private final IndexedEmbedded fieldAnnotation;

    CustomAnnotationsEmbeddedFieldConfigAdapter(IndexedEmbedded fieldAnnotation, Field field) {
        this.field = field;
        this.fieldAnnotation = fieldAnnotation;
    }
}
