package com.njfu.selection.dto;

import com.njfu.selection.entity.Student;
import com.njfu.selection.entity.Teacher;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author: lvxz
 * @Date: 2018-07-12  21:03
 */
public class AdminTeacherDto {

    private Long adminID;
    private List<Teacher> data;

    public AdminTeacherDto() {
    }

    public AdminTeacherDto(Long adminID, List<Teacher> data) {
        this.adminID = adminID;
        this.data = data;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public List<Teacher> getData() {
        return data;
    }

    public void setData(List<Teacher> data) {
        this.data = data;
    }
}
