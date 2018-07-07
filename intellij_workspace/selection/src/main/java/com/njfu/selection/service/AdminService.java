package com.njfu.selection.service;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AdminService
 * @Description: 管理员操作层
 * @Author: lvxz
 * @Date: 2018-07-07  09:31
 */
public interface AdminService {

    /**
     * 查找admin
     * @param params   json数据
     * @param request  请求协议
     * @param response 返回响应
     * @return Admin 用户对象类
     */
    ResponseInfoDTO<Admin> findAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Admin> updateAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新个人信息
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Admin> updateAdminInfoById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 忘记密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Admin> updateAdminPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response);
}