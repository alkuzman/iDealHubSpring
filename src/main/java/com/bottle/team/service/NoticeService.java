package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.AbstractNotice;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.service.helper.NoticeFilter;

import java.util.List;

/**
 * Created by Viki on 1/26/2017.
 */
public interface NoticeService extends Service<Notice> {
    Iterable<? extends BaseEntity> getNotices(Integer limit, Integer offset, NoticeFilter filter);

    Integer getCount();

    void markAsSeen();

    Notice markAsOpen(Long id);

    Iterable<Notice> saveAll(List<Notice> noticeList);
}
