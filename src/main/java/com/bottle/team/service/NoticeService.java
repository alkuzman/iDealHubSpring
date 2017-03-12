package com.bottle.team.service;

import com.bottle.team.model.sharing.AbstractNotice;
import com.bottle.team.model.sharing.Notice;

/**
 * Created by Viki on 1/26/2017.
 */
public interface NoticeService extends Service<Notice> {
    Iterable<Notice> getNotices(Integer limit, Integer offset);

    Integer getCount();
}
