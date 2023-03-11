package com.example.tolkien.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class TolkienCharacter {
    private String name;
    public int age;
    private Race race;

    public TolkienCharacter(String name, int age, Race race) {
        this.name = name;
        if (age < 0) {
            throw new IllegalArgumentException("Age is not allowed to be smaller than zero");
        }
        this.age = age;
        this.race = race;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age is not allowed to be smaller than zero");
        }
        this.age = age;
    }
}
