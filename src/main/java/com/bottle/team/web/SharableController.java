package com.bottle.team.web;

import com.bottle.team.model.sharing.Shareable;
import com.bottle.team.service.SharableService;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@RestController
@RequestMapping("/sharables")
public class SharableController {
    @Autowired
    SharableService sharableService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Shareable findOne(@PathVariable Long id) {
        Shareable shareable = this.sharableService.findById(id);
        if (shareable == null)
            throw new ResourceNotFoundException();

        return shareable;
    }
}
