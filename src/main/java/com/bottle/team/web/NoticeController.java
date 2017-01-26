package com.bottle.team.web;

import com.bottle.team.model.sharing.Notice;
import com.bottle.team.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
