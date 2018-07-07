package com.njfu.selection.service;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: StudentService
 * @Description: 学生服务层
 * @Author: lvxz
 * @Date: 2018-07-07  09:16
 */
public interface StudentService {

    /**
     * 查找student
     * @param params   json数据
     * @param request  请求协议
     * @param response 返回响应
     * @return Student 用户对象类
     */
    ResponseInfoDTO<Student> findStudentPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Student> updateStudentPasswordById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新个人信息
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Student> updateStudentInfoById(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 忘记密码
     * @param params
     * @param request
     * @param response
     * @return
     */
    ResponseInfoDTO<Student> updateStudentPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response);
}
