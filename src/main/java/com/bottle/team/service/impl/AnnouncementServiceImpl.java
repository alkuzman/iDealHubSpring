package com.bottle.team.service.impl;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.AnnouncementRepository;
import com.bottle.team.service.AnnouncementService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.AnnouncementFilter;
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
        fields[0] = "pckg.shareable.owner.name";
        fields[1] = "pckg.shareable.owner.firstName";
        fields[2] = "pckg.shareable.owner.lastName";
        fields[3] = "pckg.shareable.owner.email";
        fields[4] = "pckg.shareable.text";
        fields[5] = "pckg.shareable.title";
        fields[6] = "pckg.shareable.snackPeak";
        fields[7] = "pckg.shareable.problem.title";
        fields[8] = "pckg.shareable.problem.text";
        fields[9] = "pckg.shareable.questioner.name";
        fields[10] = "pckg.shareable.questioner.firstName";
        fields[11] = "pckg.shareable.questioner.lastName";
        fields[12] = "pckg.shareable.questioner.email";
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
        announcementRepository.deleteById(id);
    }

    @Override
    public Announcement findById(Long id) {
        return Neo4jUtils.findById(announcementRepository, id);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, AnnouncementFilter filter) {
        Iterable<? extends BaseEntity> iterable = queryService.search(query, filter.getQuery(), offset, limit, Announcement.class, this.fields);
        System.out.println("In Announcement Service");
        for (BaseEntity be : iterable) {
            System.out.println(be.getId());
        }
        return iterable;
    }
}
