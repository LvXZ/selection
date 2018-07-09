package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.TeacherDao;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Teacher;
import com.njfu.selection.service.TeacherService;
import com.njfu.selection.utils.MessageYmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("success.code")), ymlUtil.getLogin().get("success.msg"), getTeacher);
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
}
