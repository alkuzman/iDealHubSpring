package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.IndexingService;
import com.bottle.team.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AKuzmanoski on 13/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/01/2017
 */
@RestController
@RequestMapping("/search")
public class QueryController {
    final
    QueryService queryService;
    final
    IndexingService indexingService;

    @Autowired
    public QueryController(QueryService queryService, IndexingService indexingService) {
        this.queryService = queryService;
        this.indexingService = indexingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> search(@RequestParam(required = true) String query, @RequestParam Integer offset, @RequestParam Integer limit) {
        return queryService.search(query, offset, limit);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update() {
        indexingService.createIndex();
    }
}
