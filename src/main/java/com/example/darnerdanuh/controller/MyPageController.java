package com.example.darnerdanuh.controller;


import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

@RestController
public class MyPageController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private static Principal principal;

    @GetMapping("/user/profile")
    public String getProfile(){

        String name = memberRepository.findByUserIdToName(principal.getName());
        String userId = memberRepository.findByUserIdToId(principal.getName());

        JSONObject result = new JSONObject();

        result.put("userId", userId);
        result.put("name", name);

        return result.toString();
    }

    @PostMapping("/user/verifyPassword")
    public ResponseEntity verifyPassword(@RequestBody MemberDto memberDto){

        Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(RuntimeException::new);

        if(passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){

            memberRepository.save(member.passwordVerifyUpdate(true));

            return new ResponseEntity<String>("success",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("bad request",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity updateProfile(@RequestBody MemberDto memberDto, Principal principal){

        Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(RuntimeException::new);

        if(!member.isPasswordVerify()) return new ResponseEntity<String>("please verify email",HttpStatus.FORBIDDEN);

        memberRepository.save(member.nameUpdate(memberDto.getName()));

        memberRepository.save(member.passwordVerifyUpdate(false));

        return new ResponseEntity<String>("success",HttpStatus.OK);
    }
}
