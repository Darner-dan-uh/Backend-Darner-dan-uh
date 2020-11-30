package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.memo.MemoDto;
import com.example.darnerdanuh.domain.memo.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MemoController {

    @Autowired
    MemoRepository memoRepository;

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/memo/create")
    public Object createMemo(@RequestBody MemoDto memoDto, Principal principal){

        System.out.println("들어옴");
        Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(RuntimeException::new);

        System.out.println("멤버 담음");
        memoRepository.save(memoDto.toEntity(member, memoDto.getTitle(), memoDto.getContent()));
        System.out.println("리턴 직전");

        return "hello";
    }
}
