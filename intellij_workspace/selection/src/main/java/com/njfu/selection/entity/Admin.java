package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: Admin
 * @Description: 用户管理员类表
 * @Author: lvxz
 * @Date: 2018-07-07  14:55
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin {

    private Long adminID;
    private String adminName;
    @JsonIgnore
    private String password;
    private String profileImg;
    private Date birthday;
    private String gender;
    private String phone;
    private Integer enable_status;

    public Admin() {
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getEnable_status() {
        return enable_status;
    }

    public void setEnable_status(Integer enable_status) {
        this.enable_status = enable_status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", enable_status=" + enable_status +
                '}';
    }
}
