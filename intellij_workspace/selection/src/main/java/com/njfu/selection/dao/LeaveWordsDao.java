package com.njfu.selection.dao;

import com.njfu.selection.entity.LeaveWords;
import java.util.List;

/**
 * @ClassName: LeaveWordsDao
 * @Description: 留言leave_words数据库表MyBatis接口
 * @Author: lvxz
 * @Date: 2018-07-13  22:44
 */
public interface LeaveWordsDao {



    int addLeaveWords(LeaveWords leaveWords);

    List<LeaveWords> queryLeaveWordsByTeacherIdAndStudentId(LeaveWords leaveWords);


}
