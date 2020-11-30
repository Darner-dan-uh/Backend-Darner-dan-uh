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
    private Long memoId;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private Member userId;

    @Column
    private String title;
    private String content;
    private boolean fixed = false;

    public Memo(){}


    @Builder
    public Memo(Member userId, String title, String content, boolean fixed){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.fixed = fixed;
    }
}
