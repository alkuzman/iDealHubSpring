package com.bottle.team.lucene.document.visitor;

import com.bottle.team.lucene.document.DocumentFactoryImpl;
import com.bottle.team.lucene.document.TransitiveDocumentFactory;

/**
 * Created by AKuzmanoski on 07/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 07/09/2017
 */
public interface DocumentFactoryVisitor<T> {
    T visit(DocumentFactoryImpl documentFactoryImpl);

    T visit(TransitiveDocumentFactory transitiveDocumentFactory);
}
