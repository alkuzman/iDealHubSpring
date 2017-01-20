package com.bottle.team.service.helper;

import org.apache.lucene.search.Query;

/**
 * Created by AKuzmanoski on 20/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 20/01/2017
 */
public interface Filter {
    Query getQuery();
}
