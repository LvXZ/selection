package com.njfu.selection.dto;

import com.njfu.selection.entity.Project;


/**
 * @ClassName: DesignProjectDto
 * @Description: Design和Project表类的集合数据传输
 * @Author: lvxz
 * @Date: 2018-07-11  11:15
 */
public class DesignProjectDto extends Project {

    private Project project;
    private String designName;
    private Long teacherID;
    private String teacherName;
    private Integer designStatus;

    public DesignProjectDto() {
    }

    public DesignProjectDto(Project project) {
        this.project = project;
    }

    public DesignProjectDto(Project project, String designName, Long teacherID, String teacherName, Integer designStatus) {
        this.project = project;
        this.designName = designName;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.designStatus = designStatus;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
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

    public Integer getDesignStatus() {
        return designStatus;
    }

    public void setDesignStatus(Integer designStatus) {
        this.designStatus = designStatus;
    }
}
