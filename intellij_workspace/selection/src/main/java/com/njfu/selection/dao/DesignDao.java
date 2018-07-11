package com.njfu.selection.dao;

import com.njfu.selection.entity.Design;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DesignDao
 * @Description: 教师发布毕业设计表类design数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-09  16:41
 */
public interface DesignDao {

    /**
     * insert design申请
     *
     * @param design
     * @return int 1成功，0错误
     */
    int addDesign(Design design);

    List<Design> queryAllDesignByTeacherId(@Param("teacherID") Long teacherID);

    Design queryDesignInfoByDesignId(@Param("designID") String designID);

    int updateDesignEnableStatusByDesignId(Design design);

    int deleteDesignByDesignIdAndTeacherID(Design design);

    List<Design> queryAllDesignByEnableStatus10();

    List<Design> queryAllDesignByEnableStatus234();


}
