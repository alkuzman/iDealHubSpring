package com.bottle.team.lucene.config.model;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 10:00
 */
public interface ModelConfig {
    <T> boolean isIndexed(Class<T> c);
}
