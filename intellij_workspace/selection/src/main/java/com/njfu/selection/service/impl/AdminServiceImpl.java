package com.njfu.selection.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.selection.dao.AdminDao;
import com.njfu.selection.dao.DesignDao;
import com.njfu.selection.dao.StudentDao;
import com.njfu.selection.dao.TeacherDao;
import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import com.njfu.selection.entity.Design;
import com.njfu.selection.entity.Student;
import com.njfu.selection.service.AdminService;
import com.njfu.selection.utils.MessageYmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private MessageYmlUtil ymlUtil;

    @Override
    public ResponseInfoDTO<Admin> findAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {

        logger.debug("<----------findAdminPasswordById---------->");
        Admin admin = JSON.parseObject(params, Admin.class);
        Admin getAdmin = adminDao.queryAdminPasswordById(admin.getAdminID());

        response.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if (getAdmin == null) {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_2.code")), ymlUtil.getLogin().get("failure_2.msg"));
        } else {
            if (getAdmin.getPassword().equals(admin.getPassword())) {
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("success.code")), ymlUtil.getLogin().get("success.msg"), getAdmin);
            } else {
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getLogin().get("failure_1.code")), ymlUtil.getLogin().get("failure_1.msg"));
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
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), newAdmin);
                } else {
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getMysql_error().get("success.code")), ymlUtil.getUpdate().get("success.msg"));
                }

            } else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_2.code")), ymlUtil.getUpdate().get("password.failure_2.msg"));
            }

        } else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_1.code")), ymlUtil.getUpdate().get("password.failure_1.msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("info.success.code")), ymlUtil.getUpdate().get("info.success.msg"), admin);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getMysql_error().get("code")), ymlUtil.getUpdate().get("msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.success.code")), ymlUtil.getUpdate().get("password.success.msg"), admin);
        } else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getUpdate().get("password.failure_3.code")), ymlUtil.getUpdate().get("password.failure_3.msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("admin.failure_2.code")), ymlUtil.getHelp().get("admin.failure_2.msg"));
        }else{
            List<Design> designList = designDao.queryAllDesignByEnableStatus10();

            if(designList.size() == 0){
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("admin.failure_1.code")), ymlUtil.getHelp().get("admin.failure_1.msg"));
            }else{
                //有缺陷
                for(Design design:designList){
                    design.setFileAddress(teacherDao.queryTeacherNameById(design.getTeacherID()));
                }

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getHelp().get("admin.success.code")), ymlUtil.getHelp().get("admin.success.msg"),designList);
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.success_1.code")), ymlUtil.getDesign().get("update.success_1.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.failure_1.code")), ymlUtil.getDesign().get("update.failure_1.msg"));
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
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.success_2.code")), ymlUtil.getDesign().get("update.success_2.msg"));

        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(ymlUtil.getDesign().get("update.failure_2.code")), ymlUtil.getDesign().get("update.failure_2.msg"));
        }

        return responseInfoDTO;
    }
}
