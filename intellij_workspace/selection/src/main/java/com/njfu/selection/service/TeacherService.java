package com.njfu.selection.service;

import com.njfu.selection.dto.ResponseInfoDTO;

import com.njfu.selection.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: TeacherService
 * @Description: 教师服务层
 * @Author: lvxz
 * @Date: 2018-07-07  09:28
 */
public interface TeacherService {

    /**
     * 查找teacher
     * @param params   json数据
     * @param request  请求协议
     * @param response 返回响应
     * @return Teacher 用户对象类
     */
    ResponseInfoDTO<Teacher> findTeacherPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Teacher> updateTeacherPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新个人信息
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Teacher> updateTeacherInfoById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 忘记密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Teacher> updateTeacherPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 申请发布毕业设计
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> teacherApplyDesign(HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除申请的毕业设计
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> teacherDeleteDesign(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 正式发布毕业设计
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> teacherPublishDesign(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询教师自己毕业设计
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Object> teacherMyselfDesign(String params, HttpServletRequest request, HttpServletResponse response);


}
