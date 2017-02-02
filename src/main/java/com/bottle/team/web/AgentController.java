package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.AgentService;
import com.bottle.team.service.helper.AgentFilter;
import com.bottle.team.web.helper.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Viki on 1/25/2017.
 */
@RestController
@RequestMapping(value = "/agents")
public class AgentController {
    @Autowired
    AgentService agentService;

    @RequestMapping(method = RequestMethod.GET)
    public BaseEntityList findAll(@RequestParam(required = false) String query,
                                  @RequestParam(required = false) Integer offset,
                                  @RequestParam(required = false) Integer limit) {
        BaseEntityList list = new BaseEntityList();
        for (BaseEntity entity : this.agentService.findAll(query, offset, limit, new AgentFilter())) {
            list.add(entity);
        }
        return list;
    }
}
