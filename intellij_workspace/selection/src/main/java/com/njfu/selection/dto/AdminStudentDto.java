package com.njfu.selection.dto;

import com.njfu.selection.entity.Student;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author: lvxz
 * @Date: 2018-07-12  20:39
 */
public class AdminStudentDto {

    private Long adminID;
    private List<Student> data;

    public AdminStudentDto() {
    }

    public AdminStudentDto(Long adminID, List<Student> data) {
        this.adminID = adminID;
        this.data = data;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }
}
