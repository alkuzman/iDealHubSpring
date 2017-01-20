package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Searchable;
import com.bottle.team.service.helper.Filter;
import com.bottle.team.service.helper.SolutionFilter;

/**
 * Created by AKuzmanoski on 20/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 20/01/2017
 */
public interface SearchableService extends Service<Searchable> {
    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, Filter filter);
}
