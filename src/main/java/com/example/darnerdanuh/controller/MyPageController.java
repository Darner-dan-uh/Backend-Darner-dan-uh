package com.example.darnerdanuh.controller;


import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

@RestController
public class MyPageController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //private Member member;

    @GetMapping("/user/profile")
    public String getProfile(Principal principal){

        String name = memberRepository.findByUserIdToName(principal.getName());
        String userId = memberRepository.findByUserIdToId(principal.getName());

        JSONObject result = new JSONObject();

        result.put("userId", userId);
        result.put("name", name);

        return result.toString();
    }

    @PostMapping("/user/verifyPassword")
    public Object verifyPassword(@RequestBody MemberDto memberDto, Principal principal){

        Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(RuntimeException::new);

        if(passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){

            memberRepository.save(member.passwordVerifyUpdate(true));

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
