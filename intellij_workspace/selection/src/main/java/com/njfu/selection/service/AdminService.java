package com.njfu.selection.service;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 获取教师申请信息
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> adminGetTeacherDesign(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 管理员确定教师申请
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> updateAdminEnsureDesign(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 管理员驳回教师申请
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> updateAdminOpposeDesign(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 读取excel表格
     * @param file
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> adminReadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

    /**
     * 文件注册学生
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> adminInsert2Students(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 文件注册老师
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> adminInsert2Teachers(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 冻结解冻用户账号
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> adminBlockedUser(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 读取头条信息
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> readHeadLine(HttpServletRequest request, HttpServletResponse response);

    /**
     * 添加头条信息
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> addHeadLine(String params, HttpServletRequest request, HttpServletResponse response);

}
