package com.njfu.selection.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Student;
import com.njfu.selection.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Introduction: 登录管理 后端服务
 * Created by  LvXZ  on 2018/4/27.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    //日志输出
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**********************************普通员工操作**********************************/
    /**
     * 用户登录管理
     **/
    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Student> userLogin(@RequestBody String params, HttpServletRequest request,
                                              HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();//将json格式的数据解析成类的对象
        JsonNode paramJson = objectMapper.readTree(params);

        return userService.findStudentPasswordById(paramJson, request, response);

    }


}
