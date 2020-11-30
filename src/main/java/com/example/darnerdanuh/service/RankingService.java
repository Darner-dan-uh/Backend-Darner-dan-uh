package com.example.darnerdanuh.service;

import com.example.darnerdanuh.domain.ResponseJson;
import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.word.Word;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {
    @Autowired
    private final MemberRepository memberRepository;

    public String getRanking(int count){
        List<Member> members = memberRepository.findAllByOrderByWordCntDesc();

        if(count > members.size()){
            return "wrong input";
        }

        try{
            JSONArray result = new JSONArray();

            for (int i = 0; i < count; i++) {
                JSONObject obj = new JSONObject();
                obj.put("name", members.get(i).getName());
                obj.put("rank", i + 1);
                result.put(obj);
            }

            JSONObject result_json = new JSONObject();
            result_json.put("ranking", result);

            return result_json.toString();
        }catch (JSONException e){
            return e.getMessage();
        }
    }

    public String getMyRanking(String id){
        List<Member> members = memberRepository.findAllByOrderByWordCntDesc();
        String mem = memberRepository.findByUserIdToId(id);

        JSONObject obj = new JSONObject();

        for (int i=0;i<members.size();i++) {
            if(members.get(i).getUserId().equals(mem)){
                obj.put("rank", i + 1);
                break;
            }
        }

        return obj.toString();
    }
}
