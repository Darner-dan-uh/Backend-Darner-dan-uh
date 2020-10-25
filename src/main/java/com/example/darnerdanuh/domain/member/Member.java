package com.example.darnerdanuh.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="member")
@Getter
@ToString
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

    public Member(String userId, String name, String email, String password){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String userId, String name, String email, String password){
        return new Member(userId, name, email, password);
    }
}
