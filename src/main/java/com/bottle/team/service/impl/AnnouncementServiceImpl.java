package com.bottle.team.service.impl;

import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.repository.AnnouncementRepository;
import com.bottle.team.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public Iterable<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement save(Announcement object) {
        return announcementRepository.save(object);
    }

    @Override
    public Announcement add(Announcement object) {
        return save(object);
    }

    @Override
    public void delete(Long id) {
        announcementRepository.delete(id);
    }

    @Override
    public Announcement findById(Long id) {
        return announcementRepository.findOne(id);
    }
}
