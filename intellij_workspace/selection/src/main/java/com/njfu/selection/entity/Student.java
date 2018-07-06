package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: 用户学生类表
 * @Description: Student
 * @Author: lvxz
 * @Date: 2018-07-06  10:51
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    private Long studentID;
    private String studentName;
    @JsonIgnore
    private String password;
    private String studentClass;
    private String profileImg;
    private Date birthday;
    private String gender;
    private String phone;
    private Long studentQq;
    private Integer enable_status;

    public Student() {
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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

    public Long getStudentQq() {
        return studentQq;
    }

    public void setStudentQq(Long studentQq) {
        this.studentQq = studentQq;
    }

    public Integer getEnable_status() {
        return enable_status;
    }

    public void setEnable_status(Integer enable_status) {
        this.enable_status = enable_status;
    }
}
