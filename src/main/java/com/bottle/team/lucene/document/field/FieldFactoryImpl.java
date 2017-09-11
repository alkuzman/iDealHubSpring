package com.bottle.team.lucene.document.field;

import org.apache.lucene.index.IndexableField;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 12:49
 */
@Component
public class FieldFactoryImpl implements FieldFactory {
    @Override
    public List<IndexableField> getFields(Object object) {
        // TODO @AKuzmanoski #feature implement this method
        return new LinkedList<>();
    }
}
