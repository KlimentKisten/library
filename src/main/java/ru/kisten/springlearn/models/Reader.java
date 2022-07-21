package ru.kisten.springlearn.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Reader {
    private int id;
    @NotEmpty(message = "name should not be empty")
    private String name;
    @Min(value = 0, message = "age should be greater or equals zero")
    private int age;

    public Reader(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Reader() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
