package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.VerifyInfo;
import com.example.darnerdanuh.service.RankingService;
import com.example.darnerdanuh.service.SignUpService;
import com.example.darnerdanuh.service.VerifyCodeService;
import com.fasterxml.jackson.core.io.JsonEOFException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final VerifyCodeService verifyCodeService;

    private final SignUpService signUpService;

    private final RankingService rankingService;

    @Autowired
    private MemberRepository memberRepository;

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

    @GetMapping("/user/level")
    public Object getLevel(Principal principal) {

        try {
            Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(RuntimeException::new);

            System.out.println(member.getWordCnt());
            int word_cnt = member.getWordCnt();

            JSONObject obj = new JSONObject();
            obj.put("level", word_cnt/10);
            return obj.toString();

        } catch (NullPointerException e) {
            JSONObject obj = new JSONObject();
            obj.put("level", "으힝ㅇㅎ;ㅇㅎ;ㅇ");
            return obj.toString();
        }
    }
}
