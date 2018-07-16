package com.example.kotlin.model.bean;

import java.io.Serializable;

/**
 * Created by chenqi on 2018/7/16 11:04
 * Email:cq_816102@163.com
 * Tips:
 */
public class Student implements Serializable{

    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
