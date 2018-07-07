package com.njfu.selection.service.impl;

import com.njfu.selection.dto.ResponseInfoDTO;
import com.njfu.selection.entity.Admin;
import com.njfu.selection.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AdminServiceImpl
 * @Description: 管理员操作层具体实现
 * @Author: lvxz
 * @Date: 2018-07-07  09:32
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public ResponseInfoDTO<Admin> findAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminPasswordById(String params, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminInfoById(String params, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseInfoDTO<Admin> updateAdminPasswordByOther(String params, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
