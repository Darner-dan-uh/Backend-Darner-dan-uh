package com.example.darnerdanuh.domain.word;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long word_num;

    @Column(length = 45, nullable = false)
    private String word_eng;
    private String word_kor;

    @Column(nullable = false)
    private int word_id;

    @Column(nullable = false)
    private boolean word_used = false;

    public Word(){}

    @Builder
    public Word(Long word_num, String word_eng, String word_kor, int word_id, boolean word_used){
        this.word_num = word_num;
        this.word_eng = word_eng;
        this.word_kor = word_kor;
        this.word_id = word_id;
        this.word_used = word_used;
    }
}
