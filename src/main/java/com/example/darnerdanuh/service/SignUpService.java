package com.example.darnerdanuh.service;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberDto;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.VerifyInfo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder encode;
    private final EmailService service;

    @SneakyThrows
    public ResponseJson signUp(MemberDto memberDto) {
        Member member = memberRepository.save(
                Member.builder()
                        .email(memberDto.getEmail())
                        .name(memberDto.getName())
                        .password(encode.encode(memberDto.getPassword()))
                        .userId(memberDto.getUserId())
                        .build()
        );
        service.sendSimpleMessage(memberDto.getEmail());

        ResponseJson json = new ResponseJson();
        VerifyInfo info = new VerifyInfo(memberDto.getEmail(), EmailService.ePw);
        memberRepository.save(member.verifyCodeUpdate(info.code));

        System.out.println("이메일/코드 : " + info.email + " " + info.code);
        json.setMessage("success");

        return json;
    }
}
