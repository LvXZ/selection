package com.njfu.selection.redis;


/**
 * key设置接口类
 */
public interface KeyPrefix {

	//过期时间
	public int expireSeconds();
	//key前缀
	public String getPrefix();
	
}
