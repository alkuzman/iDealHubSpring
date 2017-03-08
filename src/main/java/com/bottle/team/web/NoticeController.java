package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.service.NoticeService;
import com.bottle.team.web.helper.BaseEntityIterable;
import com.bottle.team.service.helper.AgentFilter;
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
}
