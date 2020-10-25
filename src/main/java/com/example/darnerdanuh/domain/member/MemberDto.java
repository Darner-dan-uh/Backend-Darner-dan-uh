package com.example.darnerdanuh.domain.member;

import lombok.Data;

@Data
public class MemberDto {
    private String userId;
    private String name;
    private String email;
    private String password;
}
