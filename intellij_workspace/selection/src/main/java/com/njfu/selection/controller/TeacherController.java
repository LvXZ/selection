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


    /***teacher申请发布毕业设计、删除申请的毕业设计、正式发布毕业设计、查询教师自己毕业设计、停止该毕设的选择、教师完结该毕设*********************************/
    /**
     * 申请发布毕业设计
     * designName,teacherID,file
     */
    @PostMapping(value = "/apply_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherApplyDesign(HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherApplyDesign(request, response);
    }

    /**
     * 删除申请的毕业设计
     * designID, teacherID
     */
    @PostMapping(value = "/delete_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherDeleteDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherDeleteDesign(params, request, response);
    }

    /**
     * 正式发布毕业设计
     * designID, teacherID
     */
    @PostMapping(value = "/publish_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherPublishDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherPublishDesign(params, request, response);
    }

    /**
     * 查询教师自己毕业设计
     * teacherID
     */
    @PostMapping(value = "/myself_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherMyselfDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherMyselfDesign(params, request, response);
    }

    /**
     * 停止该毕设的选择
     * designID, teacherID
     */
    @PostMapping(value = "/stop_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherStopDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherStopDesign(params, request, response);
    }

    /**
     * 教师完结该毕设
     * designID, teacherID
     */
    @PostMapping(value = "/end_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> teacherEndDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return teacherService.teacherEndDesign(params, request, response);
    }

}
