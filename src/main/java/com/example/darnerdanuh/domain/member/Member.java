package com.example.darnerdanuh.domain.member;

import lombok.*;

import javax.persistence.*;

@Entity(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Member verifyCodeUpdate(String verifyCode) {
        this.verifyCode = verifyCode;

        return this;
    }

    public Member permittedUpdate(boolean permitted) {
        this.permitted = permitted;

        return this;
    }
}
