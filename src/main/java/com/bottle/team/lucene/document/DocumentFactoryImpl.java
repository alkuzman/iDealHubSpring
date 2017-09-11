package com.bottle.team.lucene.document;

import com.bottle.team.lucene.config.model.ModelConfig;
import com.bottle.team.lucene.document.field.FieldFactory;
import com.bottle.team.lucene.document.visitor.DocumentFactoryVisitor;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 * This class represents a factory for creating {@link Document}s from an object.
 * The document is a representation of the JAVA object in the lucene index.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @implNote This is the basic implementation of the factory. It creates only one document, representation only for the outer object,
 * not including the documents for the inner objects (relations).
 * @since 06/09/2017
 */
@Component
@Primary
public class DocumentFactoryImpl implements DocumentFactory {
    private final FieldFactory fieldFactory;
    private final ModelConfig modelConfig;

    @Autowired
    public DocumentFactoryImpl(FieldFactory fieldFactory, ModelConfig modelConfig) {
        this.fieldFactory = fieldFactory;
        this.modelConfig = modelConfig;
    }

    /**
     * {@inheritDoc}
     *
     * @implNote This implementation will return only one document, representation only for the outer object,
     * not including the documents for the inner objects (relations).
     */
    @Override
    public List<Document> create(Object object) {
        if (!modelConfig.isIndexed(object.getClass())) {
            return Collections.emptyList();
        }
        Document document = new Document();
        fieldFactory.getFields(object).forEach(document::add);
        return Collections.singletonList(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T accept(DocumentFactoryVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
