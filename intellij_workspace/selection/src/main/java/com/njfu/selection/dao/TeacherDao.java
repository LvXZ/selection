package com.njfu.selection.dao;

import com.njfu.selection.entity.Student;
import com.njfu.selection.entity.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: TeacherDao
 * @Description: 用户teacher数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-07  15:00
 */
public interface TeacherDao {

    /**
     * 通过id查找teacherPassword
     *
     * @param teacherID 学生账号id
     * @return Teacher 用户教师对象类
     */
    Teacher queryTeacherPasswordById(@Param("teacherID") Long teacherID);

    /**
     * 通过id查找teacherInfo
     *
     * @param teacherID 学生账号id
     * @return Teacher
     */
    Teacher queryTeacherInfoById(@Param("teacherID") Long teacherID);

    /**
     * 通过id更新密码
     * @param teacher
     * @return int 1成功，0错误
     */
    int updateTeacherPasswordById(Teacher teacher);

    /**
     * 通过其他字段更新密码
     * @param teacher
     * @return
     */
    int updateTeacherPasswordByOther(Teacher teacher);

    /**
     * 通过id更新个人信息
     * @param teacher
     * @return
     */
    int updateTeacherInfoById(Teacher teacher);
}
