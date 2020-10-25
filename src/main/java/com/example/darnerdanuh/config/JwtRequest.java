package com.example.darnerdanuh.config;

import lombok.Data;

@Data
public class JwtRequest {

    private String userId;
    private String password;
}
