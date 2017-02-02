package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.relationship.Recipient;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.repository.NoticeRepository;
import com.bottle.team.service.NoticeService;
import com.bottle.team.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        for (Recipient recipient: object.getRecipients())
            recipient.setNotice(object);
        return noticeRepository.save(object);
    }

    @Override
    public Notice add(Notice object) {
        object = save(object);
        for (Recipient recipient : object.getRecipients()) {
            String username = recipient.getAgent().getEmail();
            this.webSocketService.sendToUser(username, "/topic/notices", object);
        }
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
        return this.noticeRepository.getNotices(email, limit, offset);
    }

    @Override
    public Integer getCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        String email = userContext.getUsername();
        return this.noticeRepository.getCount(email);
    }
}
