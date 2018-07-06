package com.njfu.selection.dao;

import com.njfu.selection.entity.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: 用户student数据库表接口
 * @Description: StudentDao
 * @Author: lvxz
 * @Date: 2018-07-06  10:49
 */
public interface StudentDao {

    /**
     * 通过id查找student
     *
     * @param student_id 学生账号id
     * @return Student 用户学生对象类
     */
    Student queryStudentPasswordById(@Param("student_id") Long student_id);


}
