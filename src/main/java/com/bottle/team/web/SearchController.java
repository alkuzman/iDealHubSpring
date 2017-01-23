package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.IndexingService;
import com.bottle.team.service.SearchableService;
import com.bottle.team.service.helper.SearchableFilter;
import com.bottle.team.web.helper.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by AKuzmanoski on 13/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/01/2017
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    final
    private SearchableService searchableService;
    final
    private IndexingService indexingService;

    @Autowired
    public SearchController(SearchableService searchableService, IndexingService indexingService) {
        this.searchableService = searchableService;
        this.indexingService = indexingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BaseEntityList search(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        BaseEntityList list = new BaseEntityList();
        for (BaseEntity entity : searchableService.findAll(query, offset, limit, new SearchableFilter())) {
            list.add(entity);
        }
        return list;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update() {
        indexingService.createIndex();
    }
}
