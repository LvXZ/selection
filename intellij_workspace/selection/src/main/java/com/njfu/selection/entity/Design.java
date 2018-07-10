package com.njfu.selection.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @ClassName: Design
 * @Description: 教师发布毕业设计表类
 * @Author: lvxz
 * @Date: 2018-07-09  16:32
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Design {

    private String designID;
    private String designName;
    private Long teacherID;
    private Date createTime;
    private String fileAddress;
    private String fileName;
    private Integer enableStatus;

    public Design() {
    }

    public Design(String designID, String designName, Long teacherID, Date createTime, String fileAddress, String fileName, Integer enableStatus) {
        this.designID = designID;
        this.designName = designName;
        this.teacherID = teacherID;
        this.createTime = createTime;
        this.fileAddress = fileAddress;
        this.fileName = fileName;
        this.enableStatus = enableStatus;
    }

    public String getDesignID() {
        return designID;
    }

    public void setDesignID(String designID) {
        this.designID = designID;
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
}
