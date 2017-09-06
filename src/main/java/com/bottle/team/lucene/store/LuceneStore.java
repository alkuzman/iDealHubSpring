package com.bottle.team.lucene.store;

import org.apache.lucene.document.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
public interface LuceneStore {
    void save(Document document) throws IOException;

    void save(List<Document> documents) throws IOException;

    void update(Document document) throws IOException;

    void update(List<Document> documents) throws IOException;

    void remove(Long id) throws IOException;

    void remove(List<Long> ids) throws IOException;

    void clear() throws IOException;
}
