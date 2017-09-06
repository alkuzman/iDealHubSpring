package com.bottle.team.lucene.document;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
@Component
public class NonTransitiveDocumentFactory implements DocumentFactory {
    @Override
    public List<Document> create(Object object) {
        // TODO @AKuzmanoski #feature create real implementation of lucene document creation
        return new LinkedList<>();
    }
}
