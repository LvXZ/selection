package com.njfu.selection.entity;

/**
 * @ClassName: LeaveWords
 * @Description: 教师学生留言
 * @Author: lvxz
 * @Date: 2018-07-13  22:39
 */
public class LeaveWords {

    private Long studentID;
    private Long teacherID;
    private String words;
    private Integer flag;

    public LeaveWords() {
    }

    public LeaveWords(Long studentID, Long teacherID, String words, Integer flag) {
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.words = words;
        this.flag = flag;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
