package com.bottle.team.lucene.document;

/**
 * Created by AKuzmanoski on 09/09/2017.
 * <p>
 * This is an abstract implementation of the {@link DocumentFactory} which decorates the already existing {@link DocumentFactory}.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @apiNote API remains the same, just delegation takes place instead of implementation
 * @implNote Implementation is additional logic to the API calls and delegation to the existing implementation
 * @see {@link DocumentFactory}
 * @since 09/09/2017
 */
public abstract class DocumentFactoryDecorator implements DocumentFactory {
    /**
     * That will be decorated by this class and its subclasses
     */
    private final DocumentFactory decoratee;

    /**
     * This is the only constructor of the {@link DocumentFactoryDecorator}
     *
     * @param decoratee which will be decorated by this class
     */
    public DocumentFactoryDecorator(DocumentFactory decoratee) {
        this.decoratee = decoratee;
    }

    /**
     * {@inheritDoc}
     */
    public DocumentFactory getDecoratee() {
        return decoratee;
    }
}
