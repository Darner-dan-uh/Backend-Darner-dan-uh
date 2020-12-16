package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.member.Member;
import com.example.darnerdanuh.domain.member.MemberRepository;
import com.example.darnerdanuh.domain.memo.Memo;
import com.example.darnerdanuh.domain.memo.MemoDto;
import com.example.darnerdanuh.domain.memo.MemoRepository;
import com.example.darnerdanuh.domain.word.Word;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class MemoController {

    @Autowired
    MemoRepository memoRepository;

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/memo/create")
    public ResponseEntity<String> createMemo(@RequestBody MemoDto memoDto, Principal principal){

        Memo memo = memoRepository.save(
                Memo.builder()
                .title(memoDto.getTitle())
                .content(memoDto.getContent())
                .userId(principal.getName())
                .build()
        );

        return new ResponseEntity<String>("success", HttpStatus.CREATED);
    }

    @GetMapping("/memo/list")
    public String listMemo(Principal principal){

        List<Memo> memo = memoRepository.findByUserId(principal.getName());
        System.out.println("A");

        try{
            JSONArray result = new JSONArray();
            for(int i = 0; i < memo.size(); i++){
                JSONObject obj = new JSONObject();
                obj.put("title", memo.get(i).getTitle());
                obj.put("content", memo.get(i).getContent());
                result.put(obj);
            }
            JSONObject result_json = new JSONObject();
            result_json.put("content", result);

            return result_json.toString();
        }catch (JSONException e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/memo/delete")
    public ResponseEntity<String> deleteMemo(@RequestParam(value = "idx") Long idx){
        memoRepository.deleteById(idx);

        return new ResponseEntity<String>("success", HttpStatus.NO_CONTENT);
    }
}
