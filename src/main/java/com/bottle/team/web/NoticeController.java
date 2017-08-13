package com.bottle.team.web;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.model.sharing.NoticeList;
import com.bottle.team.service.NoticeService;
import com.bottle.team.service.helper.NoticeFilter;
import com.bottle.team.web.helper.BaseEntityIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        String email = userContext.getUsername();
        NoticeFilter filter = new NoticeFilter(email);
        return new BaseEntityIterable(noticeService.getNotices(limit, offset, filter));
    }

    @RequestMapping(value = "/bulk", method = RequestMethod.POST)
    public BaseEntityIterable<Notice> addNotices(@RequestBody @Valid NoticeList noticeList) {
        return new BaseEntityIterable<Notice>(noticeService.saveAll(noticeList.getNotices()));
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
        return this.noticeService.markAsOpen(id);
    }
}
