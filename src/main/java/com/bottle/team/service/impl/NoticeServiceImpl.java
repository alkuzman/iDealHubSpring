package com.bottle.team.service.impl;

import com.bottle.team.model.sharing.Notice;
import com.bottle.team.repository.NoticeRepository;
import com.bottle.team.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Viki on 1/26/2017.
 */
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeRepository noticeRepository;

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
        return save(object);
    }

    @Override
    public void delete(Long id) {
        noticeRepository.delete(id);
    }

    @Override
    public Notice findById(Long id) {
        return noticeRepository.findOne(id);
    }
}
