package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.service.helper.AnnouncementFilter;
import com.bottle.team.service.helper.IdeaFilter;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
public interface AnnouncementService extends Service<Announcement> {
    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, AnnouncementFilter filter);
}
