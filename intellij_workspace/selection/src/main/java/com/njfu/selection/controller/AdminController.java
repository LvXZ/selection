package com.njfu.selection.controller;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import com.njfu.selection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AdminService
 * @Description: 管理员操作后端
 * @Author: lvxz
 * @Date: 2018-07-07  09:26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**********************************admin登陆、更新密码、更新个人信息、忘记密码**********************************/
    /**
     * 登陆
     * adminID,password
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Admin> adminLogin(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.findAdminPasswordById(params, request, response);
    }

    /**
     * 更新密码
     * adminID,password,new_password
     */
    @PostMapping(value = "/update_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Admin> adminUpdatePassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.updateAdminPasswordById(params, request, response);
    }

    /**
     * 更新个人信息
     * adminID,birthday,phone
     */
    @PostMapping(value = "/update_info", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Admin> adminUpdateInfo(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.updateAdminInfoById(params, request, response);
    }

    /**
     * 忘记密码
     * adminID,new_password,adminName,phone
     */
    @PostMapping(value = "/forget_password", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Admin> adminForgetPassword(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.updateAdminPasswordByOther(params, request, response);
    }


    /**********************************admin获取教师申请信息、确定教师申请、驳回教师申请**********************************/
    /**
     * 获取教师申请信息
     * adminID,adminName
     */
    @PostMapping(value = "/get_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminGetDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.adminGetTeacherDesign(params, request, response);
    }

    /**
     * 管理员确定教师申请
     * designID
     */
    @PostMapping(value = "/ensure_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminEnsureDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.updateAdminEnsureDesign(params, request, response);
    }

    /**
     * 管理员驳回教师申请
     * designID
     */
    @PostMapping(value = "/oppose_design", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminOpposeDesign(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.updateAdminOpposeDesign(params, request, response);
    }



    /**********************************admin读取excel表格,文件注册学生,文件注册老师,冻结解冻用户账号*********************************/
    /**
     * 读取excel表格
     * radio, file
     */
    @PostMapping(value = "/read_excel", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> readExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        return adminService.adminReadExcel(file, request, response);
    }

    /**
     * 文件注册学生
     * adminID, students
     */
    @PostMapping(value = "/insert2_students", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminInsert2Students(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.adminInsert2Students(params, request, response);
    }

    /**
     * 文件注册老师
     * adminID, teachers
     */
    @PostMapping(value = "/insert2_teachers", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminInsert2Teachers(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.adminInsert2Teachers(params, request, response);
    }

    /**
     * 冻结解冻用户账号
     * adminID, teachers
     */
    @PostMapping(value = "/blocked_user", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> adminBlockedUser(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.adminBlockedUser(params, request, response);
    }

    /**********************************admin读取头条信息，添加头条信息*********************************/
    /**
     * 读取头条信息
     *
     */
    @PostMapping(value = "/read_head_line", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> readHeadLine(HttpServletRequest request, HttpServletResponse response) {
        return adminService.readHeadLine(request, response);
    }

    /**
     * 添加头条信息
     *
     */
    @PostMapping(value = "/add_head_line", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Object> addHeadLine(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) {
        return adminService.addHeadLine(params, request, response);
    }

}
