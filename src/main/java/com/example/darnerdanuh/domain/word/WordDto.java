package com.example.darnerdanuh.domain.word;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WordDto {

    private String word_eng;
    private String word_kor;
    private int word_id;
    private int number;
    private boolean word_used = false;
}
