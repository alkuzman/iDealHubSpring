package com.bottle.team.lucene.config.model;

import com.bottle.team.lucene.exceptions.NotIndexedClassException;
import com.bottle.team.lucene.exceptions.NotIndexedFieldException;

import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 10:00
 */
public interface ModelConfigFactory {
    <T> boolean isIndexed(Class<T> c);

    <T> ClassConfig getConfig(Class<T> c) throws NotIndexedClassException;

    boolean isIndexed(Field field);

    FieldConfig getConfig(Field field) throws NotIndexedFieldException;
}
