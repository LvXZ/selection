package com.njfu.selection.redis.key;

import com.njfu.selection.redis.BasePrefix;

/**
 * @ClassName: StudentKey
 * @Description: StudentKey为KeyPrefix前缀类的具体实现类
 * @Author: lvxz
 * @Date: 2018-07-29  14:35
 */
public class StudentKey extends BasePrefix {


    public StudentKey(String prefix) {
        super(prefix);
    }

    public StudentKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static StudentKey getById = new StudentKey("id");
    public static StudentKey getByName = new StudentKey("name");
}
