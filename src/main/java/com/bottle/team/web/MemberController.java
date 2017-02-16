package com.bottle.team.web;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 09/10/2016.
 */
@RestController()
@RequestMapping(value = "/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping(method = RequestMethod.POST)
    public Member save(@RequestBody Member member) {
        return memberService.save(member);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }
}
