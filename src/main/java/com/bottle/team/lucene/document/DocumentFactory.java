package com.bottle.team.lucene.document;

import com.bottle.team.lucene.document.visitor.DocumentFactoryVisitor;
import org.apache.lucene.document.Document;

import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 * This class represents a factory for creating {@link Document}s from an object.
 * The document is a representation of the JAVA object in the lucene index.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @apiNote The API of this factory is simple. It has two methods:
 * <ul>
 * <li>{@link #create(Object)} which is business logic method for creating {@link Document}'s out of objects</li>
 * <li>{@link #accept(DocumentFactoryVisitor)} which is just accessor method for extending
 * the functionality of factory via Visitor Pattern</li>
 * </ul>
 * @see Document more about lucene documents
 * @since 06/09/2017
 */
public interface DocumentFactory {
    /**
     * Creates a list of {@link Document}'s which are lucene representation of the object given as a parameter.
     * Then you can use the {@link Document}'s to create the index.
     *
     * @param object for which the {@link Document}'s will be created.
     * @return list of {@link Document}'s which are lucene representation of the object given as a parameter.
     */
    List<Document> create(Object object);

    /**
     * Accessor method which can accept visitor in order to extend the functionality of the factory by using Visitor Pattern.
     *
     * @param visitor who does the visiting of this factory
     * @param <T>     this type is the returning type of the visitor
     * @return a result which type is taken from the return type of the visitor. By calling this method you can return whatever you want (its up to the visitor)
     */
    <T> T accept(DocumentFactoryVisitor<T> visitor);
}
