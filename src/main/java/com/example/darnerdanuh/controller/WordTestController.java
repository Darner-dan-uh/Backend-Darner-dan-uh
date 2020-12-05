package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.member.SaveData;
import com.example.darnerdanuh.domain.word.Word;
import com.example.darnerdanuh.domain.word.WordRepository;
import com.example.darnerdanuh.service.RankingService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordTestController {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    RankingService rankingService;

    @GetMapping("/word/test")
    public String TestWord(){
        List<Word> word = wordRepository.findAllByWord_used();

        try{
            JSONArray result = new JSONArray();
            for(int i = 0; i < word.size(); i++){
                JSONObject obj = new JSONObject();
                obj.put("korea", word.get(i).getWord_kor());
                obj.put("english", word.get(i).getWord_eng());
                result.put(obj);

                Word wordUpdate = wordRepository.save(
                        Word.builder()
                                .word_num(word.get(i).getWord_num())
                                .word_eng(word.get(i).getWord_eng())
                                .word_kor(word.get(i).getWord_kor())
                                .word_id(word.get(i).getWord_id())
                                .word_used(false)
                                .build()
                );
            }
            JSONObject result_json = new JSONObject();
            result_json.put("content", result);

            return result_json.toString();
        }catch (JSONException e){
            return e.getMessage();
        }
    }

    @PostMapping("/word/savedata")
    public String SaveWordCount(@RequestBody SaveData data){
        return rankingService.UpdateUserWordCount(data);
    }
}
