package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: Teacher
 * @Description: 用户教师类表
 * @Author: lvxz
 * @Date: 2018-07-07  14:55
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher {

    private Long teacherID;
    private String teacherName;
    @JsonIgnore
    private String password;
    private String academy;
    private String profileImg;
    private Date birthday;
    private String gender;
    private String phone;
    private Long teacherQQ;
    private Integer enableStatus;

    public Teacher() {
    }

    public Teacher(Long teacherID, String teacherName, String password, String academy, Integer enableStatus) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.password = password;
        this.academy = academy;
        this.enableStatus = enableStatus;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTeacherQQ() {
        return teacherQQ;
    }

    public void setTeacherQQ(Long teacherQQ) {
        this.teacherQQ = teacherQQ;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                ", password='" + password + '\'' +
                ", academy='" + academy + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", teacherQQ=" + teacherQQ +
                ", enable_status=" + enableStatus +
                '}';
    }
}
