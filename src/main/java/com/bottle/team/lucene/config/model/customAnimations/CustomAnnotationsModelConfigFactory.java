package com.bottle.team.lucene.config.model.customAnimations;

import com.bottle.team.lucene.annotations.Indexed;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.lucene.config.model.ClassConfig;
import com.bottle.team.lucene.config.model.FieldConfig;
import com.bottle.team.lucene.config.model.ModelConfigFactory;
import com.bottle.team.lucene.exceptions.NotIndexedClassException;
import com.bottle.team.lucene.exceptions.NotIndexedFieldException;
import com.bottle.team.lucene.reflection.ReflectionFacade;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 10:01
 */
@Component
public class CustomAnnotationsModelConfigFactory implements ModelConfigFactory {
    private final ReflectionFacade reflection;

    public CustomAnnotationsModelConfigFactory(ReflectionFacade reflection) {
        this.reflection = reflection;
    }

    @Override
    public <T> boolean isIndexed(Class<T> c) {
        return reflection.hasAnnotation(c, Indexed.class);
    }

    @Override
    public <T> ClassConfig getConfig(Class<T> c) throws NotIndexedClassException {
        Indexed indexed = reflection.getAnnotation(c, Indexed.class);
        if (indexed == null) {
            throw new NotIndexedClassException(c);
        }
        return new CustomAnnotationsClassConfigAdapter(indexed, c);
    }

    @Override
    public boolean isIndexed(Field field) {
        return (reflection.hasAnnotation(field, com.bottle.team.lucene.annotations.Field.class) || reflection.hasAnnotation(field, IndexedEmbedded.class));
    }

    @Override
    public FieldConfig getConfig(Field field) throws NotIndexedFieldException {
        com.bottle.team.lucene.annotations.Field indexedField = reflection.getAnnotation(field, com.bottle.team.lucene.annotations.Field.class);
        if (indexedField != null) {
            return new CustomAnnotationsFieldConfigAdapter(indexedField, field);
        }
        IndexedEmbedded indexedEmbeddedField = reflection.getAnnotation(field, IndexedEmbedded.class);
        if (indexedEmbeddedField != null) {
            return new CustomAnnotationsEmbeddedFieldConfigAdapter(indexedEmbeddedField, field);
        }
        throw new NotIndexedFieldException(field);
    }
}
