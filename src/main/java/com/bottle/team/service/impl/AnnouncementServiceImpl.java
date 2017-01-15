package com.bottle.team.service.impl;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.repository.AnnouncementRepository;
import com.bottle.team.service.AnnouncementService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.AnnouncementFilter;
import com.bottle.team.service.helper.IdeaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final
    AnnouncementRepository announcementRepository;
    private final QueryService queryService;
    private String[] fields;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, QueryService queryService) {
        this.announcementRepository = announcementRepository;
        this.queryService = queryService;
    }

    @PostConstruct
    public void init() {
        fields = new String[13];
        fields[0] = "pckg.sharable.owner.name";
        fields[1] = "pckg.sharable.owner.firstName";
        fields[2] = "pckg.sharable.owner.lastName";
        fields[3] = "pckg.sharable.owner.email";
        fields[4] = "pckg.sharable.text";
        fields[5] = "pckg.sharable.title";
        fields[6] = "pckg.sharable.snackPeak";
        fields[7] = "pckg.sharable.problem.title";
        fields[8] = "pckg.sharable.problem.text";
        fields[9] = "pckg.sharable.questioner.name";
        fields[10] = "pckg.sharable.questioner.firstName";
        fields[11] = "pckg.sharable.questioner.lastName";
        fields[12] = "pckg.sharable.questioner.email";
    }

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

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, AnnouncementFilter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Announcement.class, this.fields);
    }
}
