package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.repository.NoticeRepository;
import com.bottle.team.service.NoticeService;
import com.bottle.team.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Viki on 1/26/2017.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private final
    NoticeRepository noticeRepository;
    private final
    WebSocketService webSocketService;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, WebSocketService webSocketService) {
        this.noticeRepository = noticeRepository;
        this.webSocketService = webSocketService;
    }

    @Override
    public Iterable<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice save(Notice object) {
        return noticeRepository.save(object);
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
        noticeRepository.delete(id);
    }

    @Override
    public Notice findById(Long id) {
        return noticeRepository.findOne(id);
    }

    @Override
    public Iterable<Notice> getNotices(Integer limit, Integer offset) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        String email = userContext.getUsername();
        if (offset == null) {
            offset = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        return this.noticeRepository.getNotices(email, limit, offset);
        /*List<Notice> filteredNotices = new ArrayList<Notice>();
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
        Notice notice = this.noticeRepository.findOne(id);
        Date now = new Date();
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcDate = sdf.format(now);
        notice.setOpened(now);
        return this.noticeRepository.save(notice);
    }

    @Override
    public Iterable<Notice> saveAll(List<Notice> noticeList) {
        Iterable<Notice> list = this.noticeRepository.save(noticeList);
        for (Notice object : noticeList) {
            String username = object.getRecipient().getEmail();
            this.webSocketService.sendToUser(username, "/topic/notices", object);
        }
        return list;
    }
}
