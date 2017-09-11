package com.bottle.team.lucene.document;

import com.bottle.team.lucene.document.extractor.IndexedClassExtractor;
import com.bottle.team.lucene.document.visitor.DocumentFactoryVisitor;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 * <p>
 * This class is a {@link DocumentFactoryDecorator} (decorator) of the {@link DocumentFactory}. Its responsibility is to generate {@link Document}'s for the whole dependency tree of the {@link Object} by calling the decoratee multiple times.
 * </p>
 *
 * @author AKuzmanoski
 * @version 1.0
 * @implNote It decorates the {@link DocumentFactory} by calling the decoratee for every object in the dependency tree of the object given as argument.
 * @see DocumentFactory
 * @see DocumentFactoryDecorator
 * @since 06/09/2017
 */
@Component
public class TransitiveDocumentFactory extends DocumentFactoryDecorator {
    private final IndexedClassExtractor indexedClassExtractor;

    /**
     * This is the only constructor of the {@link TransitiveDocumentFactory}
     *
     * @param decoratee which will be decorated by this class
     */
    @Autowired
    public TransitiveDocumentFactory(DocumentFactory decoratee, IndexedClassExtractor indexedClassExtractor) {
        super(decoratee);
        this.indexedClassExtractor = indexedClassExtractor;
    }

    /**
     * {@inheritDoc}
     *
     * @implNote It decorates the decoratee's {@link DocumentFactory#create(Object)} by calling it for every object in the dependency tree of the object given as argument.
     */
    @Override
    public List<Document> create(Object object) {
        // Get inner indexed objects
        List<Object> innerObjects = indexedClassExtractor.getIndexedObjectsTransitive(object);
        // Create result list of Documents
        List<Document> result = new LinkedList<>();
        // Create Document for each object in the innerObjects list and add it to the result list of Documents
        innerObjects.stream().map(o -> getDecoratee().create(object)).forEach(result::addAll);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T accept(DocumentFactoryVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
