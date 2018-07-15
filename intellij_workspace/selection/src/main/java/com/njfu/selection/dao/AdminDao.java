package com.njfu.selection.dao;

import com.njfu.selection.entity.Admin;
import com.njfu.selection.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AdminDao
 * @Description: 用户admin数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-07  15:00
 */
public interface AdminDao {

    /**
     * 通过id查找adminPassword
     *
     * @param adminID 学生账号id
     * @return Admin 管理员对象类
     */
    Admin queryAdminPasswordById(@Param("adminID") Long adminID);

    /**
     * 通过id查找adminInfo
     *
     * @param adminID 管理员账号id
     * @return
     */
    Admin queryAdminInfoById(@Param("adminID") Long adminID);

    /**
     * 通过id更新密码
     * @param admin
     * @return int 1成功，0错误
     */
    int updateAdminPasswordById(Admin admin);

    /**
     * 通过其他字段更新密码
     * @param admin
     * @return
     */
    int updateAdminPasswordByOther(Admin admin);

    /**
     * 通过id更新个人信息
     * @param admin
     * @return
     */
    int updateAdminInfoById(Admin admin);


    int updateAdminEnableStatusByAdmin_AdminID(Admin admin);

    int addAdmin(Admin admin);
}
