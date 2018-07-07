package com.njfu.selection.dao;

import com.njfu.selection.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: StudentDao
 * @Description: 用户student数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-06  10:49
 */
public interface StudentDao {

    /**
     * 通过id查找studentPassword
     *
     * @param studentID 学生账号id
     * @return Student 用户学生对象类
     */
    Student queryStudentPasswordById(@Param("studentID") Long studentID);

    /**
     * 通过id查找studentInfo
     *
     * @param studentID 学生账号id
     * @return Student 用户学生对象类
     */
    Student queryStudentInfoById(@Param("studentID") Long studentID);

    /**
     * 通过id更新密码
     * @param student
     * @return int 1成功，0错误
     */
    int updateStudentPasswordById(Student student);

    /**
     * 通过其他字段更新密码
     * @param student
     * @return
     */
    int updateStudentPasswordByOther(Student student);

    /**
     * 通过id更新个人信息
     * @param student
     * @return
     */
    int updateStudentInfoById(Student student);


}
