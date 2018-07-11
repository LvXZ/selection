package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: Project
 * @Description: 毕设项目类表
 * @Author: lvxz
 * @Date: 2018-07-10  22:40
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    private String projectID;
    private String designID;
    private Long studentID;
    private Date createTime;
    private String fileAddress;
    private String fileName;
    private Integer enableStatus;
    private Date lastEditTime;

    public Project() {
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getDesignID() {
        return designID;
    }

    public void setDesignID(String designID) {
        this.designID = designID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
