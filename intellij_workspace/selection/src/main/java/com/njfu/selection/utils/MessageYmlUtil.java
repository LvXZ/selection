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

    public static Map<String, String> mysql_error = new HashMap<>();

    public static Map<String, String> login = new HashMap<>();

    public static Map<String, String> add = new HashMap<>();

    private static Map<String, String> update = new HashMap<>();

    private static Map<String, String> file = new HashMap<>();

    private static Map<String, String> help = new HashMap<>();

    private static Map<String, String> design = new HashMap<>();

    private static Map<String, String> project = new HashMap<>();


    public Map<String, String> getMysql_error() {
        return mysql_error;
    }

    public Map<String, String> getLogin() {
        return login;
    }

    public Map<String, String> getAdd() {
        return add;
    }

    public Map<String, String> getUpdate() {
        return update;
    }

    public Map<String, String> getFile() {
        return file;
    }

    public Map<String, String> getHelp() {
        return help;
    }

    public Map<String, String> getDesign() {
        return design;
    }

    public Map<String, String> getProject() {
        return project;
    }
}
