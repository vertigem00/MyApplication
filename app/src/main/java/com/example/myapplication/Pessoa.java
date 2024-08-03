package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pessoa_tabela")
public class Pessoa {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;

    public Pessoa(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
