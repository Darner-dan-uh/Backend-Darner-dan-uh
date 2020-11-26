package com.example.darnerdanuh.service;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {
    @Autowired
    private final MemberRepository memberRepository;

    private ResponseJson getRanking(int count){
        List<Member> members = memberRepository.findAllByOrderByWordCntDesc();


        return null;
    }

}
