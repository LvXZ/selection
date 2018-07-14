package com.njfu.selection.dao;

import com.njfu.selection.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: HeadLineDao
 * @Description: 留言head_line数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-14  15:03
 */
public interface HeadLineDao {

    int addHeadLine(HeadLine headLine);


    int deleteHeadLine();


    HeadLine queryHeadLine();

}
