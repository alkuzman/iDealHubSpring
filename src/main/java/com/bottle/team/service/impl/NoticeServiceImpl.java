package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.NoticeRepository;
import com.bottle.team.service.NoticeService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.WebSocketService;
import com.bottle.team.service.helper.NoticeFilter;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Viki on 1/26/2017.
 */
@Service
@Primary
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    WebSocketService webSocketService;
    @Autowired
    QueryService queryService;
    private String[] fields;

    @PostConstruct
    public void init() {
        this.fields = new String[1];
    }

    @Override
    public Iterable<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice save(Notice object) {
        return noticeRepository.save(object, 20);
    }

    @Override
    public Notice add(Notice object) {
        object = save(object);
        String username = object.getRecipient().getEmail();
        this.webSocketService.sendToUser(username, "/topic/notices", object);

        return object;
    }

    @Override
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    @Override
    public Notice findById(Long id) {
        return Neo4jUtils.findById(noticeRepository, id);
    }

    @Override
    public Iterable<? extends BaseEntity> getNotices(Integer limit, Integer offset, NoticeFilter filter) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 20;
        }
        return queryService.search(filter.getQuery(), offset, limit, Notice.class, new Comparator<BaseEntity>() {
            @Override
            public int compare(BaseEntity o1, BaseEntity o2) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            }
        });
        /*
        return this.noticeRepository.getNotices(email, limit, offset);
        List<Notice> filteredNotices = new ArrayList<Notice>();
        for (Notice notice : notices) {
            if (notice.getRecipient().getEmail().equals(email)) {
                filteredNotices.add(notice);
                break;
            }

        }
        return filteredNotices;
        */
    }

    @Override
    public Integer getCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        String email = userContext.getUsername();
        return this.noticeRepository.getCount(email);
    }

    @Override
    public void markAsSeen() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        String username = userContext.getUsername();
        Date now = new Date();
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcDate = sdf.format(now);
        this.noticeRepository.markAsSeen(username, utcDate);
        this.webSocketService.updateCount(username, "/topic/notices/count", 0);
        return;
    }

    @Override
    public Notice markAsOpen(Long id) {
        Notice notice = Neo4jUtils.findById(noticeRepository, id);
        Date now = new Date();
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcDate = sdf.format(now);
        notice.setOpened(now);
        return this.noticeRepository.save(notice, 20);
    }

    @Override
    public Iterable<Notice> saveAll(List<Notice> noticeList) {
        Iterable<Notice> list = this.noticeRepository.save(noticeList, 20);
        for (Notice object : noticeList) {
            String username = object.getRecipient().getEmail();
            this.webSocketService.sendToUser(username, "/topic/notices", object);
        }
        return list;
    }
}
