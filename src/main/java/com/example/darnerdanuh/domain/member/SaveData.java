package com.example.darnerdanuh.domain.member;

import lombok.Data;

@Data
public class SaveData {
    public String id;
    public int count;

    public SaveData(String id, int count){
        this.id = id;
        this.count = count;
    }
}
