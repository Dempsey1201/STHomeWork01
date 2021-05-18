package com.company.work1;

import java.util.Date;

/**
 * author dongml
 * time 21/4/2021
 * description
 */
public class Student {
    /*类型7：ID为学生信息中的编号，学号字段*/
    private int ID;
    /*类型7：name为学生信息中姓名*/
    private String name;
    /*类型7：birDate为学生的生日信息*/
    private String birDate;
    /*类型7：gender为学生中的性别信息*/
    private boolean gender;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirDate() {
        return birDate;
    }

    public void setBirDate(String birDate) {
        this.birDate = birDate;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
