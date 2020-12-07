package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.config.JwtRequest;
import com.example.darnerdanuh.config.JwtResponse;
import com.example.darnerdanuh.config.JwtTokenUtil;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    MemberDto memberDto;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final Member member = userDetailService.authenticateByUserIdAndPassword
                (authenticationRequest.getUserId(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getUserId());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
