package com.example.demo.bean;

public enum Sex {

    MAIL(1, "男"),
    FEMAIL(2, "女");

    private final String name;
    private final int code;

    Sex(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

