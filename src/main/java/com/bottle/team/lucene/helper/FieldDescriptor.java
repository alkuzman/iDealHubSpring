package com.bottle.team.lucene.helper;

import com.bottle.team.lucene.annotations.Indexed;
import com.bottle.team.lucene.annotations.SortableField;
import com.bottle.team.lucene.enumerations.Analyze;
import com.bottle.team.lucene.enumerations.Index;

import java.lang.reflect.Field;

/**
 * Created by AKuzmanoski on 13/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/01/2017
 */
public class FieldDescriptor {
    private float boost = 1f;
    private String name;
    private Index index;
    private org.apache.lucene.document.Field.Store store;
    private Analyze analyze;
    private Field field;
    private Class fieldClass;
    private String namePrefix;
    private float baseBoost;
    private com.bottle.team.lucene.annotations.Field annotation;
    private boolean sorted = false;
    private boolean analyzed = false;
    private boolean stored = false;
    private boolean indexed = false;

    public FieldDescriptor(Field field, String namePrefix, float baseBoost) {
        this.field = field;
        this.namePrefix = namePrefix;
        this.baseBoost = baseBoost;
        init();
    }

    private void init() {
        annotation = field.getAnnotation(com.bottle.team.lucene.annotations.Field.class);
        analyze = annotation.analyze();
        store = annotation.store();
        index = annotation.index();
        boost = baseBoost * annotation.boost().value();
        indexed = annotation.index().equals(Index.YES);
        analyzed = annotation.analyze().equals(Analyze.YES);
        stored = annotation.store().equals(org.apache.lucene.document.Field.Store.YES);
        String fieldName = annotation.name();
        if (fieldName.equals(""))
            fieldName = field.getName();
        name = namePrefix + fieldName;
        fieldClass = field.getType();
        if (field.isAnnotationPresent(SortableField.class))
            this.sorted = true;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setBaseBoost(float baseBoost) {
        this.baseBoost = baseBoost;
    }

    public void setAnnotation(com.bottle.team.lucene.annotations.Field annotation) {
        this.annotation = annotation;
    }

    public float getBoost() {
        return boost;
    }

    public String getName() {
        return name;
    }

    public Index getIndex() {
        return index;
    }

    public org.apache.lucene.document.Field.Store getStore() {
        return store;
    }

    public Analyze getAnalyze() {
        return analyze;
    }

    public Class getFieldClass() {
        return fieldClass;
    }

    public boolean isAnalyzed() {
        return analyzed;
    }

    public boolean isStored() {
        return stored;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isIndexed() {
        return indexed;
    }
}

