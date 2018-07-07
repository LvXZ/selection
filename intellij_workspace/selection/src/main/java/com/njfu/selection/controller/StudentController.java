package com.njfu.selection.controller;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;
import com.njfu.selection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: StudentController
 * @Description: 学生操作后端
 * @Author: lvxz
 * @Date: 2018-07-07  09:13
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**********************************student登陆、更新密码、更新个人信息、忘记密码**********************************/
    /**
     * 登陆
     * studentID,password
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Student> studentLogin(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.findStudentPasswordById(params, request, response);
    }

    /**
     * 更新密码
     * studentID,password,new_password
     */
    @PostMapping(value = "/update_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Student> studentUpdatePassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.updateStudentPasswordById(params, request, response);
    }

    /**
     * 更新个人信息
     * studentID,birthday,phone,studentQQ
     */
    @PostMapping(value = "/update_info", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Student> studentUpdateInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.updateStudentInfoById(params, request, response);
    }

    /**
     * 忘记密码
     * studentID,new_password,studentName,phone
     */
    @PostMapping(value = "/forget_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Student> studentForgetPassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.updateStudentPasswordByOther(params, request, response);
    }



}
