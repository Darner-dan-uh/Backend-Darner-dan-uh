package com.example.darnerdanuh.service;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.VerifyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyCodeService {
    private final MemberRepository memberRepository;

    public ResponseJson setVerifyCode(VerifyInfo verifyInfo) {
        boolean isSuccess = false;
        Member member = memberRepository.findByEmail(verifyInfo.getEmail())
                .orElseThrow(RuntimeException::new);

        System.out.println("Input : " + verifyInfo.email + " " + verifyInfo.code);

        if (member.getVerifyCode().equals(verifyInfo.code)) {
            isSuccess = true;
            memberRepository.save(member.permittedUpdate(isSuccess));
        }

        ResponseJson json = new ResponseJson();
        String msg = String.valueOf(isSuccess);
        json.setMessage(msg);

        return json;
    }
}
