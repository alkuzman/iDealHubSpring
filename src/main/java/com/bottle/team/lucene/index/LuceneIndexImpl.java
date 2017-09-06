package com.bottle.team.lucene.index;

import com.bottle.team.lucene.LuceneIndex;
import com.bottle.team.lucene.document.DocumentFactory;
import com.bottle.team.lucene.store.LuceneStore;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
public class LuceneIndexImpl implements LuceneIndex {
    private final LuceneStore luceneStore;
    private final DocumentFactory documentFactory;

    @Autowired
    public LuceneIndexImpl(LuceneStore luceneStore, @Qualifier("nonTransitiveDocumentFactory") DocumentFactory documentFactory) {
        this.luceneStore = luceneStore;
        this.documentFactory = documentFactory;
    }

    @Override
    public void index(Object object) throws IOException {
        luceneStore.update(documentFactory.create(object));
    }

    @Override
    public void index(List<Object> objects) throws IOException {
        List<Document> documents = new LinkedList<>();
        objects.forEach(object -> documents.addAll(documentFactory.create(object)));
        luceneStore.update(documents);
    }

    @Override
    public void remove(Object object) {
        // TODO @Akuzmanoski #feature implement remove object for lucene index
    }

    @Override
    public void remove(List<Object> objects) {
        // TODO @Akuzmanoski #feature implement remove objects for lucene index
    }

    @Override
    public void clear() throws IOException {
        luceneStore.clear();
    }

    @Override
    public void removeById(Long id) throws IOException {
        luceneStore.remove(id);
    }

    @Override
    public void removeById(List<Long> ids) throws IOException {
        luceneStore.remove(ids);
    }

    @Override
    public void createIndex() throws IOException {
        clear();
    }

    @Override
    public void createIndex(List<Object> objects) throws IOException {
        clear();
        index(objects);
    }

    @Override
    public void createIndex(Object object) throws IOException {
        clear();
        index(object);
    }
}
