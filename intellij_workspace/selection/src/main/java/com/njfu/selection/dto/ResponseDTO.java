package com.njfu.selection.dto;

import com.njfu.selection.aspect.HttpAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:
 * @Description:
 * @Author: lvxz
 * @Date: 2018-07-26  18:02
 */
public class ResponseDTO<T> {

    private int code;
    private String msg;
    private T data;

    /**
     * success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO<T> success(T data){
        return new ResponseDTO<T>(data);
    }

    private ResponseDTO(T data) {
        this.code = 1;
        this.msg = "success";
        this.data = data;

    }

    /**
     * fail
     * @param messageDTO
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO<T> fail(MessageDTO messageDTO){
        return new ResponseDTO<T>(messageDTO);
    }

    private ResponseDTO(MessageDTO messageDTO) {
        if(messageDTO == null){
            return;
        }
        this.code = messageDTO.getCode();
        this.msg = messageDTO.getMsg();
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
