package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.DesignDao;
import com.njfu.selection.dao.ProjectDao;
import com.njfu.selection.dao.StudentDao;
import com.njfu.selection.dao.TeacherDao;
import com.njfu.selection.dto.DesignProjectDto;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import com.njfu.selection.entity.Design;
import com.njfu.selection.entity.Project;
import com.njfu.selection.entity.Student;
import com.njfu.selection.service.StudentService;
import com.njfu.selection.utils.MessageYmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private MessageYmlUtil ymlUtil;

    @Override
    public ResponseInfoDTO<Student> findStudentPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------findStudentPasswordById---------->");
        Student student = JSON.parseObject(params, Student.class);
        Student getStudent = studentDao.queryStudentPasswordById(student.getStudentID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if (getStudent == null) {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_2.code")), ymlUtil.getLogin().get("failure_2.msg"), null);
        } else if (getStudent.getPassword().equals(student.getPassword())) {//消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("success.code")), ymlUtil.getLogin().get("success.msg"), getStudent);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_1.code")), ymlUtil.getLogin().get("failure_1.msg"), null);
        }
        return responseInfoDTO;
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
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), newStudent);
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
    public ResponseInfoDTO<Student> updateStudentInfoById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------updateStudentInfoById---------->");
        Student student = JSON.parseObject(params, Student.class);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        int flag = studentDao.updateStudentInfoById(student);
        if(flag == 1){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("info.success.code")), ymlUtil.getUpdate().get("info.success.msg"), student);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getMysql_error().get("code")), ymlUtil.getUpdate().get("msg"), null);
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), student);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_3.code")), ymlUtil.getUpdate().get("password.failure_3.msg"), null);
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("student.failure_2.code")), ymlUtil.getHelp().get("student.failure_2.msg"));
        }else{

            List<Design> designList = designDao.queryAllDesignByEnableStatus234();

            if(designList.size() == 0){
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("student.failure_1.code")), ymlUtil.getHelp().get("student.failure_1.msg"));
            }else{
                //有缺陷
                for(Design design:designList){
                    design.setFileAddress(teacherDao.queryTeacherNameById(design.getTeacherID()));
                }

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("student.success.code")), ymlUtil.getHelp().get("student.success.msg"),designList);
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
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("insert.success.code")), ymlUtil.getProject().get("insert.success.msg"),project);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("insert.failure_1.code")), ymlUtil.getProject().get("insert.failure_1.msg"));
                }

            }else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("insert.failure_3.code")), ymlUtil.getProject().get("insert.failure_3.msg"));
            }
        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("insert.failure_2.code")), ymlUtil.getProject().get("insert.failure_2.msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("student.failure_2.code")), ymlUtil.getHelp().get("student.failure_2.msg"));
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

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("select.success.code")), ymlUtil.getProject().get("select.success.msg"),designProjectDto);

            }else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("select.failure.code")), ymlUtil.getProject().get("select.failure.msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("delete.success.code")), ymlUtil.getProject().get("delete.success.msg"));
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getProject().get("delete.failure.code")), ymlUtil.getProject().get("delete.failure.msg"));
        }
        return responseInfoDTO;
    }
}
