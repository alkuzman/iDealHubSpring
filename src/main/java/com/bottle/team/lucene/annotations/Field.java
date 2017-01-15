package com.bottle.team.lucene.annotations;

import com.bottle.team.lucene.enumerations.*;

import java.lang.annotation.*;

/**
 * Created by AKuzmanoski on 11/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 11/01/2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface Field {
    String DO_NOT_INDEX_NULL = "__DO_NOT_INDEX_NULL__";
    String DEFAULT_NULL_TOKEN = "__DEFAULT_NULL_TOKEN__";

    String name() default "";

    org.apache.lucene.document.Field.Store store() default org.apache.lucene.document.Field.Store.NO;

    Index index() default Index.YES;

    Analyze analyze() default Analyze.YES;

    Norms norms() default Norms.YES;

    TermVector termVector() default TermVector.NO;

    Analyzer analyzer() default @Analyzer;

    Boost boost() default @Boost(1.0F);

    FieldBridge bridge() default @FieldBridge;

    String indexNullAs() default "__DO_NOT_INDEX_NULL__";
}
