package com.njfu.selection.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.njfu.selection.dao.StudentDao;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;
import com.njfu.selection.service.UserService;
import com.njfu.selection.utils.MessageYmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Introduction: UserService数据服务接口具体实现
 * Created by  LvXZ  on 2018/6/7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MessageYmlUtil ymlUtil;

    @Override
    public ResponseInfoDTO<Student> findStudentPasswordById(JsonNode json, HttpServletRequest request, HttpServletResponse response) {
        Long id = json.get("id").longValue();
        String password = json.get("password").textValue();
        Student getStudent = studentDao.queryStudentPasswordById(id);

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if (getStudent == null) {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("getone.failure.code")), ymlUtil.getLogin().get("getone.failure.msg"), null);
        } else if (getStudent.getPassword().equals(password)) {//消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("success.code")), ymlUtil.getLogin().get("success.msg"), getStudent);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("login.failure.code")), ymlUtil.getLogin().get("login.failure.msg"), null);
        }
        return responseInfoDTO;
    }
}
