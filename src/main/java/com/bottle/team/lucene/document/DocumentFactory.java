package com.bottle.team.lucene.document;

import org.apache.lucene.document.Document;

import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
public interface DocumentFactory {
    List<Document> create(Object object);
}
