package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: Student
 * @Description: 用户学生类表
 * @Author: lvxz
 * @Date: 2018-07-06  10:51
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    private Long studentID;
    private String studentName;
    @JsonIgnore
    private String password;
    private String academy;
    private String studentClass;
    private String profileImg;
    private Date birthday;
    private String gender;
    private String phone;
    private Long studentQQ;
    private Integer enableStatus;

    public Student() {
    }

    public Student(Long studentID, String studentName, String password, String academy, String studentClass, Integer enableStatus) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.password = password;
        this.academy = academy;
        this.studentClass = studentClass;
        this.enableStatus = enableStatus;
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

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
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

    public Long getStudentQQ() {
        return studentQQ;
    }

    public void setStudentQQ(Long studentQQ) {
        this.studentQQ = studentQQ;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", password='" + password + '\'' +
                ", academy='" + academy + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", studentQQ=" + studentQQ +
                ", enable_status=" + enableStatus +
                '}';
    }
}
