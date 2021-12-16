package com.example.data;

import java.io.Serializable;

public class ExamineBySerializable implements Serializable {

    private String name;
    private int id;
    private int number;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Examine{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}
