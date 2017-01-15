package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import org.apache.lucene.search.Query;

import java.util.List;

/**
 * Created by AKuzmanoski on 13/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/01/2017
 */
public interface QueryService {
    Iterable<? extends BaseEntity> search(String query, Integer offset, Integer limit);

    <T extends BaseEntity> Iterable<? extends BaseEntity> search(String queryString, Query query, Integer offset, Integer limit, Class<T> type, String... fields);

    <T extends BaseEntity> Iterable<? extends BaseEntity> search(Query query, Integer offset, Integer limit, Class<T> type);
}
