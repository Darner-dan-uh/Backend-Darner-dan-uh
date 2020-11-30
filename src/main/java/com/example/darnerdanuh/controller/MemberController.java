package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.VerifyInfo;
import com.example.darnerdanuh.service.RankingService;
import com.example.darnerdanuh.service.SignUpService;
import com.example.darnerdanuh.service.VerifyCodeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final VerifyCodeService verifyCodeService;

    private final SignUpService signUpService;

    private final RankingService rankingService;

    @PostMapping("/register")
    public ResponseJson saveMember(@RequestBody MemberDto memberDto) {
        return signUpService.signUp(memberDto);
    }

    @PostMapping("/verifywithemail")
    public ResponseJson verifyCode(@RequestBody VerifyInfo verifyInfo){
        return verifyCodeService.setVerifyCode(verifyInfo);
    }

    @GetMapping("/rank")
    public String getRanking(@RequestParam(value = "count") int count){
        return rankingService.getRanking(count);
    }

    @GetMapping("/myrank")
    public String getRanking(@RequestParam(value = "id") String id){
        return rankingService.getMyRanking(id);
    }
}
