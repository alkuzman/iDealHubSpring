package com.bottle.team.lucene.document.field;

import org.apache.lucene.index.IndexableField;

import java.util.List;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 12:48
 */
public interface FieldFactory {
    List<IndexableField> getFields(Object object);
}
