package com.njfu.selection.controller;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;
import com.njfu.selection.entity.Teacher;
import com.njfu.selection.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: TeacherController
 * @Description: 教师操作 后端
 * @Author: lvxz
 * @Date: 2018-07-07  09:23
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**********************************teacher登陆、更新密码、更新个人信息、忘记密码**********************************/
    /**
     * 登陆
     * teacherID,password
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Teacher> teacherLogin(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.findTeacherPasswordById(params, request, response);
    }

    /**
     * 更新密码
     * teacherID,password,new_password
     */
    @PostMapping(value = "/update_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Teacher> teacherUpdatePassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.updateTeacherPasswordById(params, request, response);
    }

    /**
     * 更新个人信息
     * teacherID,birthday,phone,teacherQQ
     */
    @PostMapping(value = "/update_info", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Teacher> teacherUpdateInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.updateTeacherInfoById(params, request, response);
    }

    /**
     * 忘记密码
     * teacherID,new_password,teacherName,phone
     */
    @PostMapping(value = "/forget_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Teacher> teacherForgetPassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.updateTeacherPasswordByOther(params, request, response);
    }
}
