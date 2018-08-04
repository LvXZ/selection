package com.njfu.selection.redis.key;

import com.njfu.selection.redis.BasePrefix;

/**
 * @ClassName:
 * @Description:
 * @Author: lvxz
 * @Date: 2018-07-29  15:51
 */
public class HeadLineKey extends BasePrefix {

    public HeadLineKey(String prefix) {
        super(prefix);
    }

    public HeadLineKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static HeadLineKey getByHead = new HeadLineKey("head");

}
