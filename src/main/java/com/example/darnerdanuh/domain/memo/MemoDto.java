package com.example.darnerdanuh.domain.memo;

import com.example.darnerdanuh.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoDto {
    Member userId;
    String title;
    String content;

    public Memo toEntity(Member userId, String title, String content){
        return Memo.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
