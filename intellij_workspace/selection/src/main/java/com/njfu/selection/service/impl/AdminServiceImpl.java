package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.*;
import com.njfu.selection.dto.*;
import com.njfu.selection.entity.*;
import com.njfu.selection.redis.RedisService;
import com.njfu.selection.redis.key.HeadLineKey;
import com.njfu.selection.redis.key.StudentKey;
import com.njfu.selection.service.AdminService;
import com.njfu.selection.utils.MessageYmlUtil;
import com.njfu.selection.utils.POIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AdminServiceImpl
 * @Description: 管理员操作层具体实现
 * @Author: lvxz
 * @Date: 2018-07-07  09:32
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private DesignDao designDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageYmlUtil ymlUtil;

    @Override
    public ResponseInfoDTO<Admin> findAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------findAdminPasswordById---------->");
        Admin admin = JSON.parseObject(params, Admin.class);
        Admin getAdmin = adminDao.queryAdminPasswordById(admin.getAdminID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if (getAdmin == null) {
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getLogin().get("failure_2.msg"));
        } else {
            if (getAdmin.getPassword().equals(admin.getPassword())) {

                if(getAdmin.getEnableStatus() == 1){
                    responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getLogin().get("success.msg"), getAdmin);
                }else{
                    responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getLogin().get("failure_3.msg"));
                }

            } else {
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getLogin().get("failure_1.msg"));
            }
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateAdminPasswordById---------->");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = null;
        try {
            paramJson = objectMapper.readTree(params);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        String new_password = paramJson.get("new_password").textValue();
        Admin oldAdmin = JSON.parseObject(params, Admin.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        Admin newAdmin = adminDao.queryAdminPasswordById(oldAdmin.getAdminID());
        if (newAdmin != null) {
            if (newAdmin.getPassword().equals(oldAdmin.getPassword())) {
                newAdmin.setPassword(new_password);
                int flag = adminDao.updateAdminPasswordById(newAdmin);
                if(flag == 1){
                    responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("password.success.msg"), newAdmin);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(-1, ymlUtil.getUpdate().get("success.msg"));
                }

            } else{
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("password.failure_2.msg"));
            }

        } else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("password.failure_1.msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminInfoById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateAdminInfoById---------->");
        Admin admin = JSON.parseObject(params, Admin.class);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = adminDao.updateAdminInfoById(admin);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("info.success.msg"), admin);
        } else {
            responseInfoDTO = new ResponseInfoDTO(-1, ymlUtil.getUpdate().get("msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateStudentPasswordByOther---------->");
        Admin admin = JSON.parseObject(params, Admin.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = adminDao.updateAdminPasswordByOther(admin);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("password.success.msg"), admin);
        } else {
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("password.failure_3.msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> adminGetTeacherDesign(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateAdminEnsureDesign---------->");
        Admin admin = JSON.parseObject(params, Admin.class);

        Admin getAdmin = adminDao.queryAdminPasswordById(admin.getAdminID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(getAdmin == null){
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getHelp().get("admin.failure_2.msg"));
        }else{
            List<Design> designList = designDao.queryAllDesignByEnableStatus10();

            if(designList.size() == 0){
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getHelp().get("admin.failure_1.msg"));
            }else{
                //有缺陷
                for(Design design:designList){
                    design.setFileAddress(teacherDao.queryTeacherNameById(design.getTeacherID()));
                }

                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getHelp().get("admin.success.msg"),designList);
            }
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> updateAdminEnsureDesign(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateAdminEnsureDesign---------->");
        Design design = JSON.parseObject(params, Design.class);
        design.setEnableStatus(1);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = designDao.updateDesignEnableStatusByDesignId(design);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getDesign().get("update.success_1.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getDesign().get("update.failure_1.msg"));
        }

        return responseInfoDTO;

    }

    @Override
    public ResponseInfoDTO<Object> updateAdminOpposeDesign(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------updateAdminEnsureDesign---------->");
        Design design = JSON.parseObject(params, Design.class);
        design.setEnableStatus(-1);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = designDao.updateDesignEnableStatusByDesignId(design);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getDesign().get("update.success_2.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getDesign().get("update.failure_2.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> adminReadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String select = request.getParameter("radio");

        //判断文件是否存在
        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(file.isEmpty()){
            responseInfoDTO = new ResponseInfoDTO(0, "上传失败,文件不存在！" ,null);
            return responseInfoDTO;
        }

        String fileName = file.getOriginalFilename();//获得文件名
        //判断文件是否是excel文件
        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
            responseInfoDTO = new ResponseInfoDTO(0, "上传失败,不是excel文件！" ,null);
            return responseInfoDTO;
        }
        if(select.equals("student")){
            return readStudentExcel(file);
        }else{
            return readTeacherExcel(file);
        }
    }

    @Override
    public ResponseInfoDTO<Object> adminInsert1Student(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------adminInsert1Student---------->");
        Student student = JSON.parseObject(params, Student.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(studentDao.queryStudentPasswordById(student.getStudentID()) == null){
            student.setPassword(String.valueOf(student.getStudentID()));
            student.setEnableStatus(1);

            int flag = studentDao.addStudent(student);//添加student
            if(flag == 1){
                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getAdd().get("success.msg"));

            }else{
                responseInfoDTO = new ResponseInfoDTO(-1, ymlUtil.getMysql_error().get("msg"));
            }
        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getAdd().get("failure.msg"));
        }
        return responseInfoDTO;

    }

    @Override
    public ResponseInfoDTO<Object> adminInsert1Teacher(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------adminInsert1Teacher---------->");
        Teacher teacher = JSON.parseObject(params, Teacher.class);
        teacher.setPassword(String.valueOf(teacher.getTeacherID()));
        teacher.setEnableStatus(1);

        int flag = teacherDao.addTeacher(teacher);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getAdd().get("success.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getAdd().get("failure.msg"));

        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> adminInsert1Admin(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------adminInsert1Admin---------->");
        Admin admin = JSON.parseObject(params, Admin.class);
        admin.setPassword(String.valueOf(admin.getAdminID()));
        admin.setEnableStatus(1);

        int flag = adminDao.addAdmin(admin);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getAdd().get("success.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getAdd().get("failure.msg"));

        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> adminInsert2Students(String params, HttpServletRequest request, HttpServletResponse response) {


        AdminStudentDto adminStudentDto = JSON.parseObject(params, AdminStudentDto.class);


        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        List<Student> error_students = new ArrayList<>();

        Admin getAdmin = adminDao.queryAdminPasswordById(Long.valueOf(adminStudentDto.getAdminID()));
        if(getAdmin != null){

            System.out.println(adminStudentDto.getData().size());

            for(int i=0;i < adminStudentDto.getData().size();i++){//排除第一行，属性

                System.out.println(adminStudentDto.getData().get(i).getStudentName());

                if( studentDao.queryStudentPasswordById(adminStudentDto.getData().get(i).getStudentID()) == null ) {

                    adminStudentDto.getData().get(i).setPassword(String.valueOf(adminStudentDto.getData().get(i).getStudentID()));
                    studentDao.addStudent(adminStudentDto.getData().get(i));//添加student

                }else{
                    error_students.add(adminStudentDto.getData().get(i));
                }
            }


            if(error_students.size() == 0){//没有错误
                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getAdd().get("success.msg"));
                return responseInfoDTO;
            }else{
                responseInfoDTO = new ResponseInfoDTO(-11, ymlUtil.getAdd().get("failure.msg"),error_students);
                return responseInfoDTO;
            }


        }else{
            responseInfoDTO = new ResponseInfoDTO(0, "管理员账号不存在");
            return responseInfoDTO;
        }


    }

    @Override
    public ResponseInfoDTO<Object> adminInsert2Teachers(String params, HttpServletRequest request, HttpServletResponse response) {

        AdminTeacherDto adminTeacherDto = JSON.parseObject(params, AdminTeacherDto.class);


        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        List<Teacher> error_teachers = new ArrayList<>();

        Admin getAdmin = adminDao.queryAdminPasswordById(adminTeacherDto.getAdminID());
        if(getAdmin != null){

            for(int i=0;i < adminTeacherDto.getData().size();i++){//排除第一行，属性
                if( teacherDao.queryTeacherPasswordById(adminTeacherDto.getData().get(i).getTeacherID()) == null ) {

                    adminTeacherDto.getData().get(i).setPassword(String.valueOf(adminTeacherDto.getData().get(i).getTeacherID()));
                    teacherDao.addTeacher(adminTeacherDto.getData().get(i));//添加teacher
                }else{
                    error_teachers.add(adminTeacherDto.getData().get(i));
                }
            }

            if(error_teachers.size() == 0){//没有错误
                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getAdd().get("success.msg"));
                return responseInfoDTO;
            }else{
                responseInfoDTO = new ResponseInfoDTO(-12, ymlUtil.getAdd().get("failure.msg"),error_teachers);
                return responseInfoDTO;
            }

        }else{
            responseInfoDTO = new ResponseInfoDTO(0, "管理员账号不存在");
            return responseInfoDTO;
        }

    }

    @Override
    public ResponseInfoDTO<Object> adminBlockedUser(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------adminBlockedUser---------->");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = null;
        try {
            paramJson = objectMapper.readTree(params);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        String radio = paramJson.get("radio").textValue();
        String enableStatus = paramJson.get("enableStatus").textValue();
        int flag = 0;

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;



        if(radio.equals("student")){
            Student student = JSON.parseObject(params, Student.class);
            flag = studentDao.updateStudentEnableStatusByAdmin_StudentID(student);

        }else if(radio.equals("teacher")){
            Teacher teacher = JSON.parseObject(params, Teacher.class);
            flag = teacherDao.updateTeacherEnableStatusByAdmin_TeacherID(teacher);
        }else{
            Admin admin = JSON.parseObject(params, Admin.class);
            flag = adminDao.updateAdminEnableStatusByAdmin_AdminID(admin);
        }

        if(flag == 1){

            if(enableStatus.equals("0")){
                responseInfoDTO = new ResponseInfoDTO(1, "冻结成功");
            }else{
                responseInfoDTO = new ResponseInfoDTO(1, "解冻成功");
            }


        }else{
            if(enableStatus.equals("0")){
                responseInfoDTO = new ResponseInfoDTO(0, "冻结失败，账号不存在，查看是否选择错误");

            }else{
                responseInfoDTO = new ResponseInfoDTO(0, "解冻失败，账号不存在，查看是否选择错误");

            }

        }

        return responseInfoDTO;
    }

    @Override
    public ResponseDTO<Object> readHeadLine(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Methods", "POST");

        String key = "";
        HeadLine headLine;
        int flag_redis = 0;

        if(!redisService.exists(HeadLineKey.getByHead,key)){//redis不存在该用户缓存，调用数据库
            headLine = headLineDao.queryHeadLine();
            System.out.println("set redis");
            flag_redis = 1;//标记登录成功后，将用户信息存入redis缓存
        }else{
            System.out.println("get redis");
            headLine = redisService.get(HeadLineKey.getByHead,key,HeadLine.class);
        }

        if (headLine == null) {
            return ResponseDTO.fail(MessageDTO.HEADLINE_FAIL);
        } else {

            if(flag_redis == 1){//存放headline于redis
                redisService.set(HeadLineKey.getByHead,key,headLine);
            }
            return ResponseDTO.success(headLine);
        }


    }

    @Override
    public ResponseDTO<Object> addHeadLine(String params, HttpServletRequest request, HttpServletResponse response) {

        HeadLine headLine = JSON.parseObject(params, HeadLine.class);
        response.setHeader("Access-Control-Allow-Methods", "POST");

        String key = "";


        if(adminDao.queryAdminInfoById(headLine.getAdminID()) == null){

            return ResponseDTO.fail(MessageDTO.ADMIN_FAIL);
        }else{
            headLineDao.deleteHeadLine();//删除之前数据库的头条信息
            //redisService.del(HeadLineKey.getByHead,key);//删除之前redis的头条信息

            int flag = headLineDao.addHeadLine(headLine);
            if(flag == 1){
                redisService.set(HeadLineKey.getByHead,key,headLine);
                System.out.println("set redis");
                return ResponseDTO.success(headLine);
                //responseInfoDTO = new ResponseInfoDTO(1, "发布成功");
            }else{
                return ResponseDTO.fail(MessageDTO.HEADLINE_FAIL_2);
            }
        }
    }




    public ResponseInfoDTO<Object> readStudentExcel(MultipartFile file){//读取学生excel文件中的用户信息

        ResponseInfoDTO responseInfoDTO;
        List<Student> students = new ArrayList<>();
        try {
            List<String[]> userList = POIUtil.readExcel(file);
            for(int i = 1;i<userList.size();i++){

                String[] users = userList.get(i);
                //Long studentID, String studentName, String password, String academy, String studentClass, Integer enableStatus

                try{
                    Student student = new Student(Long.valueOf(users[0]),users[1],users[0],users[3],users[2],1);
                    students.add(student);
                }catch (Exception e){
                    responseInfoDTO = new ResponseInfoDTO(0, "选择注册对象错误" );
                    return responseInfoDTO;
                }
            }

            responseInfoDTO = new ResponseInfoDTO(1, "上传成功" ,students);

        } catch (IOException e) {
            System.out.println("读取excel文件失败");
            responseInfoDTO = new ResponseInfoDTO(0, "读取excel文件失败！" ,null);
        }
        return responseInfoDTO;
    }

    public ResponseInfoDTO<Object> readTeacherExcel(MultipartFile file){//读取老师excel文件中的用户信息
        ResponseInfoDTO responseInfoDTO;
        List<Teacher> teachers = new ArrayList<>();
        try {
            List<String[]> userList = POIUtil.readExcel(file);
            for(int i = 1;i<userList.size();i++){
                String[] users = userList.get(i);
                //Long teacherID, String teacherName, String password, String academy, Integer enable_status
                try{
                    Teacher teacher = new Teacher(Long.valueOf(users[0]),users[1],users[0],users[2],1);
                    teachers.add(teacher);
                }catch (Exception e){
                    responseInfoDTO = new ResponseInfoDTO(0, "选择注册对象错误" );
                    return responseInfoDTO;
                }

            }
            responseInfoDTO = new ResponseInfoDTO(1, "上传成功" ,teachers);

        } catch (IOException e) {
            System.out.println("读取excel文件失败");
            responseInfoDTO = new ResponseInfoDTO(0, "读取excel文件失败！" );
        }
        return responseInfoDTO;
    }
}
