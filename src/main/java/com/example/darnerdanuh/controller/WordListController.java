package com.example.darnerdanuh.controller;

import com.example.darnerdanuh.domain.word.Word;
import com.example.darnerdanuh.domain.word.WordDto;
import com.example.darnerdanuh.domain.word.WordRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordListController {

    @Autowired
    WordRepository wordRepository;

    @PostMapping("/word/genre")
    public String WordList(@RequestBody WordDto wordDto) {

        List<Word> word = wordRepository.findByWord_id(wordDto.getWord_id());
        if(wordDto.getNumber() > word.size()){
            JSONObject obj = new JSONObject();
            obj.put("message", "wrong input");
            return obj.toString();
        }

        try{
            JSONArray result = new JSONArray();

            for (int i = 0; i < wordDto.getNumber(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("korea", word.get(i).getWord_kor());
                obj.put("english", word.get(i).getWord_eng());
                result.put(obj);
                wordDto.toEntity(true);

                Word wordUpdate = wordRepository.save(
                        Word.builder()
                                .word_num(word.get(i).getWord_num())
                                .word_eng(word.get(i).getWord_eng())
                                .word_kor(word.get(i).getWord_kor())
                                .word_id(word.get(i).getWord_id())
                                .word_used(true)
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
}
