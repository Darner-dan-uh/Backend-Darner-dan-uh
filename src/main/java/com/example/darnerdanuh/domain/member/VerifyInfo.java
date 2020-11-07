package com.example.darnerdanuh.domain.member;

import lombok.Data;

@Data
public class VerifyInfo {
    public String email;
    public String code;

    public VerifyInfo(String email, String code){
        this.email = email;
        this.code = code;
    }
}
