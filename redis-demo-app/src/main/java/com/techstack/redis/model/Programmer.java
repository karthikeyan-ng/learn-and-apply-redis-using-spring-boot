package com.techstack.redis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Programmer implements Serializable {

    private int id;
    private String company;
    private String name;
}
