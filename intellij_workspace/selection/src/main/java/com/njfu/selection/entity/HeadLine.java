package com.njfu.selection.entity;

/**
 * @ClassName: HeadLine
 * @Description: 头条表类
 * @Author: lvxz
 * @Date: 2018-07-14  15:00
 */
public class HeadLine {

    private String head;
    private String subhead;
    private String body;
    private Long adminID;

    public HeadLine() {
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }
}
