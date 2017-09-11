package com.bottle.team.lucene.document.extractor;

import com.bottle.team.lucene.annotations.Indexed;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AKuzmanoski on 09/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 09/09/2017 20:48
 */
@Component
public class IndexedClassExtractorImpl implements IndexedClassExtractor {
    @Override
    public List<Object> getIndexedObjectsTransitive(Object object) {
        // Initialize resulting list
        List<Object> result = new LinkedList<>();

        List<Field> fields = getNonSyntheticFields(object.getClass());
        // Make fields accessible
        fields.forEach(field -> field.setAccessible(true));

        // Get objects
        return null;
    }

    private List<Object> getIndexedObjets(Object object) {
        List<Field> fields = getNonSyntheticFields(object.getClass());
        // Make fields accessible
        fields.forEach(field -> field.setAccessible(true));
        return null;
    }

    private List<Field> getNonSyntheticFields(Class cls) {
        return Arrays.stream(cls.getFields()).filter(field -> !field.isSynthetic()).collect(Collectors.toList());
    }

    public boolean isIndexed(Class cls) {
        return cls.getAnnotation(Indexed.class) != null;
    }
}
