package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    final MemberRepository memberRepository;
    final PasswordEncoder encode;

    @PostMapping("/signup")
    public ResponseJson saveMember(@RequestBody MemberDto memberDto) {
        memberRepository.save(Member.createMember(memberDto.getUserId(),
                memberDto.getName(), memberDto.getEmail(),
                encode.encode(memberDto.getPassword())));

        ResponseJson json = new ResponseJson();
        json.setMessage("success");

        return json;
    }
}
