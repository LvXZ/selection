package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.*;
import com.njfu.selection.dto.DesignProjectDto;
import com.njfu.selection.dto.MessageDTO;
import com.njfu.selection.dto.ResponseDTO;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.*;
import com.njfu.selection.service.StudentService;
import com.njfu.selection.utils.MessageYmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: StudentServiceImpl
 * @Description: 学生服务层接口具体实现
 * @Author: lvxz
 * @Date: 2018-07-07  09:16
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DesignDao designDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private LeaveWordsDao leaveWordsDao;

    @Autowired
    private MessageYmlUtil ymlUtil;

    @Override
    public ResponseDTO<Student> findStudentPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        Student student = JSON.parseObject(params, Student.class);
        Student getStudent = studentDao.queryStudentPasswordById(student.getStudentID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        if (getStudent == null) {
            return ResponseDTO.fail(MessageDTO.LOGIN_FAIL_2);
        } else if (getStudent.getPassword().equals(student.getPassword())) {//消息提示工具类获取key// 正确码,字符型转为整型

            if(getStudent.getEnableStatus() == 1){
                return ResponseDTO.success(getStudent);
            }else{
                return ResponseDTO.fail(MessageDTO.LOGIN_FAIL_3);
            }
        } else {
            return ResponseDTO.fail(MessageDTO.LOGIN_FAIL_1);
        }

    }

    @Override
    public ResponseInfoDTO<Student> updateStudentPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateStudentPasswordById---------->");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = null;
        try {
            paramJson = objectMapper.readTree(params);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        String new_password = paramJson.get("new_password").textValue();
        Student oldStudent = JSON.parseObject(params, Student.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        Student newStudent = studentDao.queryStudentPasswordById(oldStudent.getStudentID());
        if (newStudent != null) {
            if (newStudent.getPassword().equals(oldStudent.getPassword())) {
                newStudent.setPassword(new_password);
                int flag = studentDao.updateStudentPasswordById(newStudent);
                if(flag == 1){
                    responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("password.success.msg"), newStudent);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("success.msg"));
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
    public ResponseInfoDTO<Student> updateStudentInfoById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateStudentInfoById---------->");
        Student student = JSON.parseObject(params, Student.class);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = studentDao.updateStudentInfoById(student);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("info.success.msg"), student);
        } else {
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Student> updateStudentPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateStudentPasswordByOther---------->");
        Student student = JSON.parseObject(params, Student.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = studentDao.updateStudentPasswordByOther(student);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getUpdate().get("password.success.msg"), student);
        } else {
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getUpdate().get("password.failure_3.msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentGetTeacherDesignInfo(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------studentGetTeacherDesignInfo---------->");
        Student student = JSON.parseObject(params, Student.class);

        Student getStudent = studentDao.queryStudentPasswordById(student.getStudentID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(getStudent == null){
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getHelp().get("student.failure_2.msg"));
        }else{

            List<Design> designList = designDao.queryAllDesignByEnableStatus234();

            if(designList.size() == 0){
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getHelp().get("student.failure_1.msg"));
            }else{
                //有缺陷
                for(Design design:designList){
                    design.setFileAddress(teacherDao.queryTeacherNameById(design.getTeacherID()));
                }
                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getHelp().get("student.success.msg"),designList);
            }
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentApplyTeacherDesignInfo(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------studentApplyTeacherDesignInfo---------->");
        Project project = JSON.parseObject(params, Project.class);

        Project getProject = projectDao.queryProjectByStudentID(project.getStudentID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        if(getProject == null){

            Design getDesign = designDao.queryDesignInfoByDesignId(project.getDesignID());
            if(getDesign != null){

                Date createTime=new Date();
                DateFormat format= new SimpleDateFormat("yyyyMMddHHmmss");
                String projectID = format.format(createTime);

                project.setProjectID(projectID);
                project.setCreateTime(createTime);
                project.setEnableStatus(0);

                int flag = projectDao.addProject(project);
                if(flag == 1){
                    responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getProject().get("insert.success.msg"),project);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getProject().get("insert.failure_1.msg"));
                }

            }else{
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getProject().get("insert.failure_3.msg"));
            }
        }else{
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getProject().get("insert.failure_2.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentGetMyselfProjectInfo(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------studentGetTeacherDesignInfo---------->");
        Student student = JSON.parseObject(params, Student.class);

        Student getStudent = studentDao.queryStudentPasswordById(student.getStudentID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(getStudent == null){
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getHelp().get("student.failure_2.msg"));
        }else{

            /**************************此处dao要优化*******************/

            Project project = projectDao.queryProjectByStudentID(student.getStudentID());
            if(project != null){

                Design design= designDao.queryDesignInfoByDesignId(project.getDesignID());

                DesignProjectDto designProjectDto = new DesignProjectDto(project);
                designProjectDto.setDesignName(design.getDesignName());
                designProjectDto.setTeacherID(design.getTeacherID());
                designProjectDto.setTeacherName(teacherDao.queryTeacherNameById(design.getTeacherID()));
                designProjectDto.setDesignStatus(design.getEnableStatus());

                responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getProject().get("select.success.msg"),designProjectDto);

            }else{
                responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getProject().get("select.failure.msg"));
            }

        }
        return responseInfoDTO;
    }


    @Override
    public ResponseInfoDTO<Object> studentDeleteApplyDesignInfo(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------studentDeleteApplyDesignInfo---------->");
        Project project = JSON.parseObject(params, Project.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO<Object> responseInfoDTO;

        int flag = projectDao.deleteProjectBypProjectIDDesignIdAndStudentID(project);
        if(flag == 1){
            //缺删除响应文件
            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getProject().get("delete.success.msg"));
        } else {
            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getProject().get("delete.failure.msg"));
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentUploadProject(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------studentUploadProject---------->");

        String projectID = request.getParameter("projectID");
        String studentID = request.getParameter("studentID");
        //获取多个文件，此处默认接收一个打包文件
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        Project getProject = projectDao.queryProjectByStudentID(Long.valueOf(studentID));
        if(!getProject.getProjectID().equals(projectID)){

            responseInfoDTO = new ResponseInfoDTO(-1, "不存在该project");
            return responseInfoDTO;
        }
        Long teacherID = designDao.queryTeacherIDByDesignId(getProject.getDesignID());

        MultipartFile file;
        BufferedOutputStream stream;

        //设置保存路径D:/download/教师/毕业开设号/教师相关介绍文件
        String filePath = "D://download//"+ teacherID +"//"+ getProject.getDesignID() +"//"+ getProject.getStudentID() +"//";
        File file_isExists = new File(filePath);
        //不存在该文件夹时，就创建
        if( !file_isExists.exists()){
            file_isExists.mkdirs();
        }

        if (files.size() > 0) {
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);

                if (!file.isEmpty()) {
                    System.out.println(file.getOriginalFilename());
                    try {
                        //是否存在文件
                        if(getProject.getEnableStatus() != 1){
                            File testFile = new File(getProject.getFileAddress());
                            testFile.delete();//删除文件
                        }

                        byte[] bytes = file.getBytes();
                        String fileName = file.getOriginalFilename();
                        //设置文件路径及名字
                        stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));

                        //getProject对象
                        getProject.setEnableStatus(2);
                        getProject.setFileAddress(filePath + fileName);
                        getProject.setFileName(fileName);
                        Date lastEditTime=new Date();
                        getProject.setLastEditTime(lastEditTime);

                        int flag = projectDao.updateAllProject(getProject);
                        if(flag == 1){
                            // 保存文件到服务器
                            stream.write(bytes);
                            stream.close();
                            responseInfoDTO = new ResponseInfoDTO(1, ymlUtil.getFile().get("upload.success.msg"),getProject);
                            return responseInfoDTO;
                        } else {
                            responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getFile().get("upload.failure_6.msg"));
                            return responseInfoDTO;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getFile().get("upload.failure_1.msg") + e.getStackTrace());
                        return responseInfoDTO;
                    }
                } else {
                    responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getFile().get("upload.failure_2.msg"));
                    return responseInfoDTO;
                }
            }
        }
        responseInfoDTO = new ResponseInfoDTO(0, ymlUtil.getFile().get("upload.failure_3.msg"));
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentAddWords(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------studentAddWords---------->");
        LeaveWords leaveWords = JSON.parseObject(params, LeaveWords.class);

        leaveWords.setFlag(0);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = leaveWordsDao.addLeaveWords(leaveWords);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(1, "留言成功",leaveWords);

        }else{

            responseInfoDTO = new ResponseInfoDTO(0, "留言失败");
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> studentGetWords(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------studentAddWords---------->");
        LeaveWords leaveWords = JSON.parseObject(params, LeaveWords.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        List<LeaveWords> leaveWordsList = leaveWordsDao.queryLeaveWordsByTeacherIdAndStudentId(leaveWords);
        if(leaveWordsList.size() == 0){
            responseInfoDTO = new ResponseInfoDTO(0, "无留言");
        }else{
            responseInfoDTO = new ResponseInfoDTO(1, "获取留言成功",leaveWordsList);
        }
        return responseInfoDTO;
    }


}
