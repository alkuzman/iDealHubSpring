package com.bottle.team.web;

import com.bottle.team.model.awards.StandardAward;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.service.AnnouncementService;
import com.bottle.team.service.helper.AnnouncementFilter;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Announcement findOne(@PathVariable Long id) {
        Announcement announcement = announcementService.findById(id);
        if (announcement == null)
            throw new ResourceNotFoundException();
        return announcement;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> findAll(
            @RequestParam(required = false) Long shareableId,
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String query
    ) {
        System.out.println(ownerId);
        AnnouncementFilter announcementFilter = new AnnouncementFilter(null, ownerId, shareableId,type);
        return announcementService.findAll(query, offset, limit, announcementFilter);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Announcement add(@RequestBody @Valid Announcement announcement) {
        return announcementService.save(announcement);
    }
}
