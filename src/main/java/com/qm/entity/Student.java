package com.qm.entity;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author qiming
 * @Date 2019/7/12 14:51
 **/
public class Student {

    private long id;
    private String name;
    private int age;
    private BigDecimal ra;
    private String ss;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public BigDecimal getRa() {
        return ra;
    }

    public void setRa(BigDecimal ra) {
        this.ra = ra;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
}
