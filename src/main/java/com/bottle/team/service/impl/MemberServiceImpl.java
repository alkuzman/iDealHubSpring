package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.MemberRepository;
import com.bottle.team.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 2/16/2017.
 */
@Service
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Iterable<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member object) {
        return memberRepository.save(object);
    }

    @Override
    public Member add(Member object) {
        return memberRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Member findById(Long id) {
        return Neo4jUtils.findById(memberRepository, id);
    }
}
