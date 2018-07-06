package com.njfu.selection.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Introduction: 用户Student数据服务接口
 * Created by  LvXZ  on 2018/6/7.
 */
public interface UserService {

    /**
     * 查找student
     *
     * @param json     json
     * @param request  请求协议
     * @param response 返回响应
     * @return Student 用户对象类
     */
    ResponseInfoDTO<Student> findStudentPasswordById(JsonNode json, HttpServletRequest request, HttpServletResponse response);
}
