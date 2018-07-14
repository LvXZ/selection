package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.*;
import com.njfu.selection.dto.DesignProjectDto;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Design;
import com.njfu.selection.entity.LeaveWords;
import com.njfu.selection.entity.Project;
import com.njfu.selection.entity.Teacher;
import com.njfu.selection.service.TeacherService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TeacherServiceImpl
 * @Description: 教师服务层接口具体实现
 * @Author: lvxz
 * @Date: 2018-07-07  09:31
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    private final static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DesignDao designDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private LeaveWordsDao leaveWordsDao;

    @Autowired
    private MessageYmlUtil ymlUtil;


    @Override
    public ResponseInfoDTO<Teacher> findTeacherPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------findTeacherPasswordById---------->");
        Teacher teacher = JSON.parseObject(params, Teacher.class);
        Teacher getTeacher = teacherDao.queryTeacherPasswordById(teacher.getTeacherID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if (getTeacher == null) {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_2.code")), ymlUtil.getLogin().get("failure_2.msg"), null);
        } else {

            if (getTeacher.getPassword().equals(teacher.getPassword())) {

                if(getTeacher.getEnableStatus() == 1){
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("success.code")), ymlUtil.getLogin().get("success.msg"), getTeacher);
                }else{
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_3.code")), ymlUtil.getLogin().get("failure_3.msg"));
                }


            } else {
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_1.code")), ymlUtil.getLogin().get("failure_1.msg"), null);
            }
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Teacher> updateTeacherPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------updateTeacherPasswordById---------->");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = null;
        try {
            paramJson = objectMapper.readTree(params);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        String new_password = paramJson.get("new_password").textValue();
        Teacher oldTeacher = JSON.parseObject(params, Teacher.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        Teacher newTeacher = teacherDao.queryTeacherPasswordById(oldTeacher.getTeacherID());
        if (newTeacher != null) {
            if (newTeacher.getPassword().equals(oldTeacher.getPassword())) {
                newTeacher.setPassword(new_password);
                int flag = teacherDao.updateTeacherPasswordById(newTeacher);
                if(flag == 1){
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), newTeacher);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getMysql_error().get("success.code")), ymlUtil.getUpdate().get("success.msg"), null);
                }

            } else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_2.code")), ymlUtil.getUpdate().get("password.failure_2.msg"), null);
            }

        } else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_1.code")), ymlUtil.getUpdate().get("password.failure_1.msg"), null);
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Teacher> updateTeacherInfoById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateTeacherInfoById---------->");
        Teacher teacher = JSON.parseObject(params, Teacher.class);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = teacherDao.updateTeacherInfoById(teacher);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("info.success.code")), ymlUtil.getUpdate().get("info.success.msg"), teacher);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getMysql_error().get("code")), ymlUtil.getUpdate().get("msg"), null);
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Teacher> updateTeacherPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateTeacherPasswordByOther---------->");
        Teacher teacher = JSON.parseObject(params, Teacher.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = teacherDao.updateTeacherPasswordByOther(teacher);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), teacher);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_3.code")), ymlUtil.getUpdate().get("password.failure_3.msg"), null);
        }
        return responseInfoDTO;
    }



    @Override
    public ResponseInfoDTO<Object> teacherApplyDesign(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------teacherApplyDesign---------->");

        String designName = request.getParameter("designName");
        String teacherID = request.getParameter("teacherID");
        //获取多个文件，此处默认接收一个打包文件
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        Date createTime=new Date();
        DateFormat format= new SimpleDateFormat("yyyyMMddHHmmss");
        String designID = format.format(createTime);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        MultipartFile file;
        BufferedOutputStream stream;

        //设置保存路径D:/download/教师/毕业开设号/教师相关介绍文件
        String filePath = "D://download//"+ teacherID +"//"+ designID +"//";
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
                        byte[] bytes = file.getBytes();
                        String fileName = file.getOriginalFilename();
                        //设置文件路径及名字
                        stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
                        //design对象
                        Design addDesign = new Design(designID, designName, Long.valueOf(teacherID), createTime, filePath + fileName, fileName,0);

                        int flag = designDao.addDesign(addDesign);
                        if(flag == 1){
                            // 保存文件到服务器
                            stream.write(bytes);
                            stream.close();

                            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getFile().get("upload.success.code")), ymlUtil.getFile().get("upload.success.msg"),addDesign);
                            return responseInfoDTO;
                        } else {
                            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getFile().get("upload.failure_6.code")), ymlUtil.getFile().get("upload.failure_6.msg"));
                            return responseInfoDTO;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getFile().get("upload.failure_1.code")), ymlUtil.getFile().get("upload.failure_1.msg") + e.getStackTrace());
                        return responseInfoDTO;
                    }
                } else {
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getFile().get("upload.failure_2.code")), ymlUtil.getFile().get("upload.failure_2.msg"));
                    return responseInfoDTO;
                }
            }
        }
        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getFile().get("upload.failure_3.code")), ymlUtil.getFile().get("upload.failure_3.msg"));
        return responseInfoDTO;
    }



    @Override
    public ResponseInfoDTO<Object> teacherDeleteDesign(String params, HttpServletRequest request, HttpServletResponse response) {
        Design design = JSON.parseObject(params, Design.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO<Object> responseInfoDTO;

        int flag = designDao.deleteDesignByDesignIdAndTeacherID(design);
        if(flag == 1){
            //缺删除响应文件
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("delete.success.code")), ymlUtil.getDesign().get("delete.success.msg"));
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("delete.failure.code")), ymlUtil.getDesign().get("delete.failure.msg"));
        }
        return responseInfoDTO;
    }



    @Override
    public ResponseInfoDTO<Object> teacherPublishDesign(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------teacherPublishDesign---------->");
        Design design = JSON.parseObject(params, Design.class);
        design.setEnableStatus(2);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = designDao.updateDesignEnableStatusByDesignId(design);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.success_3.code")), ymlUtil.getDesign().get("update.success_3.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.failure_3.code")), ymlUtil.getDesign().get("update.failure_3.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> teacherMyselfDesign(String params, HttpServletRequest request, HttpServletResponse response) {

        Teacher teacher = JSON.parseObject(params, Teacher.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO<Object> responseInfoDTO;
        List<Design> designList = designDao.queryAllDesignByTeacherId(teacher.getTeacherID());

        if(designList.size() == 0){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("teacher.failure.code")), ymlUtil.getHelp().get("teacher.failure.msg"));
        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("teacher.success.code")), ymlUtil.getHelp().get("teacher.success.msg"),designList);
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> teacherStopDesign(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------teacherStopDesign---------->");
        Design design = JSON.parseObject(params, Design.class);
        design.setEnableStatus(3);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = designDao.updateDesignEnableStatusByDesignId(design);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.success_4.code")), ymlUtil.getDesign().get("update.success_4.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.failure_4.code")), ymlUtil.getDesign().get("update.failure_4.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> teacherEndDesign(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------teacherEndDesign---------->");
        Design design = JSON.parseObject(params, Design.class);
        design.setEnableStatus(4);

        Project project = new Project(design.getDesignID(),3);


        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = designDao.updateDesignEnableStatusByDesignId(design);
        int flag2 = projectDao.updateProjectEnableStatusByDesignId(project);

        if(flag == 1 && flag2 ==1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.success_5.code")), ymlUtil.getDesign().get("update.success_5.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.failure_5.code")), ymlUtil.getDesign().get("update.failure_5.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> getStudentProject(String params, HttpServletRequest request, HttpServletResponse response) {

        Teacher teacher = JSON.parseObject(params, Teacher.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO<Object> responseInfoDTO;
        List<Design> designList = designDao.queryAllDesignByTeacherIDAndEnableStatus234(teacher.getTeacherID());

        if(designList.size() == 0){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("teacher.failure.code")), ymlUtil.getHelp().get("teacher.failure.msg"));
            return responseInfoDTO;
        }else{


            /*******dao需要优化********/

            List<DesignProjectDto> designProjectDtoList = new ArrayList<>();

            for(Design design:designList){

                List<Project> projectList = projectDao.queryProjectByDesignID(design.getDesignID());

                if(projectList.size() != 0){

                    for(Project project:projectList){

                        DesignProjectDto designProjectDto = new DesignProjectDto(project);
                        designProjectDto.setDesignName(design.getDesignName());
                        designProjectDto.setTeacherID(design.getTeacherID());
                        designProjectDto.setTeacherName(studentDao.queryStudentNameById(project.getStudentID()));
                        designProjectDto.setDesignStatus(design.getEnableStatus());

                        designProjectDtoList.add(designProjectDto);

                    }


                }

            }

            if(designProjectDtoList.size() == 0){
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("teacher.failure_1.code")), ymlUtil.getHelp().get("teacher.failure_1.msg"));
                return responseInfoDTO;
            }else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("teacher.success_1.code")), ymlUtil.getHelp().get("teacher.success_1.msg"),designProjectDtoList);
                return responseInfoDTO;
            }


        }

    }

    @Override
    public ResponseInfoDTO<Object> ensureStudentProject(String params, HttpServletRequest request, HttpServletResponse response) {


        logger.debug("<----------ensureStudentProject---------->");
        Project project = JSON.parseObject(params, Project.class);
        project.setEnableStatus(1);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = projectDao.updateProjectEnableStatusByProjectId(project);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("update.success_1.code")), ymlUtil.getProject().get("update.success_1.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("update.failure_1.code")), ymlUtil.getProject().get("update.failure_1.msg"));
        }

        return responseInfoDTO;


    }

    @Override
    public ResponseInfoDTO<Object> opposeStudentProject(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------ensureStudentProject---------->");
        Project project = JSON.parseObject(params, Project.class);
        project.setEnableStatus(-1);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = projectDao.updateProjectEnableStatusByProjectId(project);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("update.success_2.code")), ymlUtil.getProject().get("update.success_2.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("update.failure_2.code")), ymlUtil.getProject().get("update.failure_2.msg"));
        }

        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> teacherAddWords(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------teacherAddWords---------->");
        LeaveWords leaveWords = JSON.parseObject(params, LeaveWords.class);

        leaveWords.setFlag(1);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = leaveWordsDao.addLeaveWords(leaveWords);

        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(111, "留言成功",leaveWords);

        }else{

            responseInfoDTO = new ResponseInfoDTO(-111, "留言失败");
        }
        return responseInfoDTO;
    }

    @Override
    public ResponseInfoDTO<Object> teacherGetWords(String params, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("<----------teacherGetWords---------->");
        LeaveWords leaveWords = JSON.parseObject(params, LeaveWords.class);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        List<LeaveWords> leaveWordsList = leaveWordsDao.queryLeaveWordsByTeacherIdAndStudentId(leaveWords);
        if(leaveWordsList.size() == 0){
            responseInfoDTO = new ResponseInfoDTO(-112, "无留言");
        }else{
            responseInfoDTO = new ResponseInfoDTO(112, "获取留言成功",leaveWordsList);
        }
        return responseInfoDTO;
    }


}
