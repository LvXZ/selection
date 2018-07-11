package com.njfu.selection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: HtmlController
 * @Description: 网页响应服务后端
 * @Author: lvxz
 * @Date: 2018-07-07  20:19
 */

@Controller
public class HtmlController {

    @RequestMapping("/")
    public String login1Page() {
        return "/login.html";
    }

    @RequestMapping("/login")
    public String login2Page() {
        return "/login.html";
    }

    @RequestMapping("/index_student")
    public String indexStudentPage() {
        return "/index_student.html";
    }

    @RequestMapping("/index_teacher")
    public String indexTeacherPage() {
        return "/index_teacher.html";
    }

    @RequestMapping("/index_admin")
    public String indexAdminPage() {
        return "/index_admin.html";
    }

    @RequestMapping("/apply_teacher")
    public String applyTeacherPage() {
        return "/apply_teacher.html";
    }

    @RequestMapping("/inspect_admin")
    public String inspectAdminPage() {
        return "/inspect_admin.html";
    }

    @RequestMapping("/apply_student")
    public String applyStudentPage() {
        return "/apply_student.html";
    }

    @RequestMapping("/upload_student")
    public String uploadStudentPage() {
        return "/upload_student.html";
    }

    @RequestMapping("/inspect_teacher")
    public String inspectTeacherPage() {
        return "/inspect_teacher.html";
    }
}
