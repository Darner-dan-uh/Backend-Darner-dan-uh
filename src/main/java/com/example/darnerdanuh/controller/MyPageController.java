package com.example.darnerdanuh.controller;


import com.example.darnerdanuh.domain.member.MemberRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MyPageController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/user/profile")
    public String getProfile(Principal principal){

        String name = memberRepository.findByUserIdToName(principal.getName());
        String userId = memberRepository.findByUserIdToId(principal.getName());

        JSONObject result = new JSONObject();

        result.put("userId", userId);
        result.put("name", name);

        return result.toString();
    }
}
