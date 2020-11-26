package com.example.darnerdanuh.domain.member;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MemberDto {
    private String userId;
    private String name;
    private String email;
    private String password;
    private boolean permitted;
    private boolean passwordVerify;
}
