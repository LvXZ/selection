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



    /**********************************student获取教师发布毕设信息、申请教师发布毕设、查看自己的申请、删除自己的申请、学生上传毕业设计*********************************/
    /**
     * 获取教师发布毕设信息
     * studentID
     */
    @PostMapping(value = "/get_teacher_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentGetTeacherDesignInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentGetTeacherDesignInfo(params, request, response);
    }


    /**
     * 申请教师发布毕设
     * studentID,designID
     */
    @PostMapping(value = "/apply_teacher_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentApplyTeacherDesignInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentApplyTeacherDesignInfo(params, request, response);
    }


    /**
     * 查看自己的申请
     * studentID
     */
    @PostMapping(value = "/get_myself_project", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentGetMyselfProjectInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentGetMyselfProjectInfo(params, request, response);
    }


    /**
     * 删除自己的申请
     * studentID,designID,projectID
     */
    @PostMapping(value = "/delete_apply_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentDeleteApplyDesignInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentDeleteApplyDesignInfo(params, request, response);
    }


    /**
     * 学生上传毕业设计
     * projectID,studentID,file
     */
    @PostMapping(value = "/upload_project", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentUploadProject(HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentUploadProject(request, response);
    }

    /**********************************student添加留言，查询留言*********************************/
    /**
     * 添加留言
     * studentID,teacherID,words
     */
    @PostMapping(value = "/add_words", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentAddWords(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentAddWords(params, request, response);
    }

    /**
     * 查询留言
     * studentID,teacherID
     */
    @PostMapping(value = "/get_words", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> studentGetWords(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return studentService.studentGetWords(params, request, response);
    }



}
