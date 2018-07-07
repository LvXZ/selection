package com.njfu.selection.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MessageYmlUtil
 * @Description: bean来接收配置信息
 * @Author: lvxz
 * @Date: 2018-07-05  22:27
 */

@Component
@ConfigurationProperties(prefix = "message")
public class MessageYmlUtil {

    private Map<String, String> mysql_error = new HashMap<>();

    private Map<String, String> login = new HashMap<>();

    private Map<String, String> add = new HashMap<>();

    private Map<String, String> update = new HashMap<>();


    public Map<String, String> getMysql_error() {
        return mysql_error;
    }

    public void setMysql_error(Map<String, String> mysql_error) {
        this.mysql_error = mysql_error;
    }

    public Map<String, String> getLogin() {
        return login;
    }

    public void setLogin(Map<String, String> login) {
        this.login = login;
    }

    public Map<String, String> getAdd() {
        return add;
    }

    public void setAdd(Map<String, String> add) {
        this.add = add;
    }

    public Map<String, String> getUpdate() {
        return update;
    }

    public void setUpdate(Map<String, String> update) {
        this.update = update;
    }

}
