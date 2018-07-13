package com.njfu.selection.dao;

import com.njfu.selection.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ProjectDao
 * @Description: 学生申请毕业设计表类project数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-10  22:49
 */
public interface ProjectDao {

    int addProject(Project project);


    int deleteProjectBypProjectIDDesignIdAndStudentID(Project project);

    Project queryProjectByStudentID(@Param("studentID") Long studentID);

    List<Project> queryProjectByDesignID(@Param("designID") String designID);

    int updateProjectEnableStatusByProjectId(Project project);

    int updateAllProject(Project project);

    int updateProjectEnableStatusByDesignId(Project project);


}
