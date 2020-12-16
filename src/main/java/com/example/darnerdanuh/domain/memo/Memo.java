package com.example.darnerdanuh.domain.memo;

import com.example.darnerdanuh.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long memoId;

    //@ManyToOne
    //@JoinColumn(name="userId", referencedColumnName = "user_id")
    @Column(name = "user_id")
    private String userId;

    @Column
    private String title;
    private String content;
    private boolean fixed = false;

    public Memo(){}


    public Memo titleUpdate(String title) {
        this.title = title;
        return this;
    }

    public Memo contentUpdate(String content) {
        this.content = content;
        return this;
    }


    @Builder
    public Memo(String userId, String title, String content, boolean fixed){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.fixed = fixed;
    }
}
