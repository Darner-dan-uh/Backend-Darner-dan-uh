package com.example.darnerdanuh.domain.word;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordDto {

    private String word_eng;
    private String word_kor;
    private int word_id;
    private int number;
    private boolean word_used = false;

    public Word toEntity(boolean word_used){
        return Word.builder()
                .word_used(word_used)
                .build();
    }
}
