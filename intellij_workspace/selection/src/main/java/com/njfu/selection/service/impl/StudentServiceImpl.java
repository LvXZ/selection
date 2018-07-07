package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.StudentDao;
import com.njfu.selection.dto.ResponseInfoDTO;
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
}
