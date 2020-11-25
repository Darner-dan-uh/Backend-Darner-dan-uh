package com.example.darnerdanuh.controller;


import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.MyPageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// 닉네임 비밀번호만 보여줌.,
@RestController
public class MyPageController {

    MemberRepository memberRepository;

    MyPageDto myPageDto;

    @GetMapping("/mypage/profile")
    public Object getProfile(@RequestBody MemberDto memberDto){
        Optional<Member> member = memberRepository.findByUserId(memberDto.getUserId());

        return member;
    }
}
