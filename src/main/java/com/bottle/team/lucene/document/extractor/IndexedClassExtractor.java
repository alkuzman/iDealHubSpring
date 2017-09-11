package com.bottle.team.lucene.document.extractor;

import java.util.List;

/**
 * Created by AKuzmanoski on 09/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 09/09/2017 20:46
 */
public interface IndexedClassExtractor {
    List<Object> getIndexedObjectsTransitive(Object object);
}
