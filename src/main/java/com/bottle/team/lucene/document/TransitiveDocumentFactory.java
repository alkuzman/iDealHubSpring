package com.bottle.team.lucene.document;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
@Component
public class TransitiveDocumentFactory implements DocumentFactory {
    @Override
    public List<Document> create(Object object) {
        // TODO @AKuzmanoski #feature Implement the Transitive creation of lucene documents
        return null;
    }
}
