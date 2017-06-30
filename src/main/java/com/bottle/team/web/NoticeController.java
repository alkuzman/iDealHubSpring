package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.AbstractNotice;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.service.NoticeService;
import com.bottle.team.web.helper.BaseEntityIterable;
import com.bottle.team.web.helper.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Viki on 1/26/2017.
 */
@RestController
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @RequestMapping(method = RequestMethod.POST)
    public Notice addNotice(@RequestBody @Valid Notice notice) {
        return noticeService.add(notice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Notice getNotice(@PathVariable Long id) {
        return noticeService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public BaseEntityIterable<Notice> getNotices(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset
    ) {
        return new BaseEntityIterable<Notice>(noticeService.getNotices(limit, offset));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Integer getCount() {
        return noticeService.getCount();
    }

    @RequestMapping(value = "/seen", method = RequestMethod.PUT)
    public Void markAsSeen() {
        this.noticeService.markAsSeen();
        return null;
    }

    @RequestMapping(value = "/{id}/opened")
    public Notice markAsOpened(@PathVariable Long id) {
        return this.noticeService.findById(id);
    }
}
