package com.example.darnerdanuh.domain.member;

import com.example.darnerdanuh.domain.memo.Memo;
import lombok.*;

import javax.persistence.*;

@Entity(name = "member")
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long user_num;

    @Column(unique = true, name = "user_id")
    private String userId;

    @Column
    private String name;
    private String email;
    private String password;
    private boolean permitted = false;
    private String verifyCode;
    private boolean passwordVerify = false;

    public Member nameUpdate(String name){
        this.name = name;

        return this;
    }

    public Member passwordVerifyUpdate(boolean passwordVerify) {
        this.passwordVerify = passwordVerify;

        return this;
    }

    @Column(name = "word_cnt")
    private int wordCnt = 0;

    @Column(name = "stack_time")
    private double stackTime = 0.0;

    public Member verifyCodeUpdate(String verifyCode) {
        this.verifyCode = verifyCode;

        return this;
    }

    public Member permittedUpdate(boolean permitted) {
        this.permitted = permitted;

        return this;
    }

<<<<<<< HEAD
    public Member(){}

    @Builder
    public Member(String email, String name, String password, String userId){
        this.email = email;
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

=======
    public Member learnedWordUpdate(int count){
        this.wordCnt += count;

        return this;
    }

    public Member learnedTimeUpdate(double time){
        this.stackTime += time;

        return this;
    }
>>>>>>> de210e84a63d45f6a13cc4cce16acd685670ad69
}
