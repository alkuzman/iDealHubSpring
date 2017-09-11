package com.bottle.team.lucene.index;

import com.bottle.team.lucene.LuceneIndex;
import com.bottle.team.lucene.document.DocumentFactory;
import com.bottle.team.lucene.store.LuceneStore;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 * <p>
 * <p>This class is simple implementation of the {@link LuceneIndex} (see for more API details). It only delegates responsibilities to:
 * <ol>
 * <li>{@link DocumentFactory} for {@link Object} to {@link Document} mapping</li>
 * <li>{@link LuceneStore} for storing the {@link Document}'s created from {@link DocumentFactory}</li>
 * </ol></p>
 *
 * @author AKuzmanoski
 * @version 1.0
 * @see LuceneIndex
 * @see LuceneStore
 * @see DocumentFactory
 * @see Document
 * @since 06/09/2017
 */
@Component
public class LuceneIndexImpl implements LuceneIndex {
    /**
     * {@inheritDoc}
     */
    private final LuceneStore luceneStore;
    /**
     * This factory helps us to map recived {@link Object}'s to lucene {@link Document}'s.
     */
    private final DocumentFactory documentFactory;

    @Autowired
    public LuceneIndexImpl(LuceneStore luceneStore, DocumentFactory documentFactory) {
        this.luceneStore = luceneStore;
        this.documentFactory = documentFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void index(Object object) throws IOException {
        luceneStore.update(documentFactory.create(object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void index(List<Object> objects) throws IOException {
        List<Document> documents = new LinkedList<>();
        objects.forEach(object -> documents.addAll(documentFactory.create(object)));
        luceneStore.update(documents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Object object) {
        // TODO @Akuzmanoski #feature implement remove object for lucene index
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(List<Object> objects) {
        // TODO @Akuzmanoski #feature implement remove objects for lucene index
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() throws IOException {
        luceneStore.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeById(Long id) throws IOException {
        luceneStore.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeById(List<Long> ids) throws IOException {
        luceneStore.remove(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createIndex() throws IOException {
        clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createIndex(List<Object> objects) throws IOException {
        clear();
        index(objects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createIndex(Object object) throws IOException {
        clear();
        index(object);
    }
}
