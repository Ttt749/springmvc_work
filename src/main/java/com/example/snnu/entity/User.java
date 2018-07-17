package com.example.snnu.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private static final long serializable = 1L;
    @JSONField(name="id")
    private long id;
    @JSONField(name="userName")
    private String userName;
    @JSONField(name="passWord")
    private String passWord;
    @JSONField(name="age")
    private int age;
    @JSONField(name="regTime")
    private Date regTime;
    @JSONField(name="sex")
    private String sex;
    @JSONField(name="birthday")
    private Date birthday;
    @JSONField(name="phoneId")
    private String phoneId;
    private String computerId;
    private String userImage;

    public User() {
    }

    public User(long id, String userName, String passWord, int age, Date regTime, String sex, Date birthday, String phoneId, String computerId, String userImage) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
        this.regTime = regTime;
        this.sex = sex;
        this.birthday = birthday;
        this.phoneId = phoneId;
        this.computerId = computerId;
        this.userImage = userImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getComputerId() {
        return computerId;
    }

    public void setComputerId(String computerId) {
        this.computerId = computerId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", regTime=" + regTime +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", phoneId='" + phoneId + '\'' +
                ", computerId='" + computerId + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }

}
