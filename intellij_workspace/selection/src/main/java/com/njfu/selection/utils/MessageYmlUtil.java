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
 * <p>
 * * 加载yaml配置文件的方法
 * * spring-boot更新到1.5.2版本后locations属性无法使用
 * * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 * * 故现在把数据放到application.yml文件中,spring-boot启动时会加载
 * *
 */

@Component
//@PropertySource("classpath:messages/message.yml")
@ConfigurationProperties(prefix = "message") //接收message.yml中的message下面的属性
public class MessageYmlUtil {

    private Map<String, String> login = new HashMap<>(); //接收login里面的属性值


    public Map<String, String> getLogin() {
        return login;
    }

    public void setLogin(Map<String, String> login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "MessageYmlUtil{" +
                "login=" + login + "key:" + login.values() +
                '}';
    }
}
