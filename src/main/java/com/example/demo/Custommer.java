package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Custommer {
    
    private Integer id;
    private String name;
    private String email;
    private String adress;
    private Integer age;
    private String password;

    public Custommer(Integer id, String name, String email, String adress, Integer age, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.age = age;
        this.password = password;
    }
    
}
