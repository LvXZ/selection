package com.njfu.selection.dto;

import com.njfu.selection.entity.Admin;

/**
 * @ClassName: RequestInfoDTO
 * @Description: 传入数据对象
 * @Author: lvxz
 * @Date: 2018-07-12  20:30
 */
public class RequestInfoDTO<T> {

    private Long adminID;

    private T data;

    public RequestInfoDTO() {
    }

    public RequestInfoDTO(Long adminID, T data) {
        this.adminID = adminID;
        this.data = data;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
