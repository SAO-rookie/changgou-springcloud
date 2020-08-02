package com.snowy.changgou.content.tool;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @auther snowy
 * @date 2020/7/9 - 22:28
 */
@Builder
@ToString
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回标记：成功标记= true，失败标记= ERROR")
    private boolean flag;//是否成功

    @ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
    private int code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;


    public static <T> Result <T> ok() {
        return restResult(CommonConstants.CORRECT,null, CommonConstants.SUCCESS, null);
    }

    public static <T> Result <T> ok(T data) {
        return restResult(CommonConstants.CORRECT,data, CommonConstants.SUCCESS, null);
    }

    public static <T> Result <T> ok(T data, String msg) {
        return restResult(CommonConstants.CORRECT,data, CommonConstants.SUCCESS, msg);
    }

    public static <T> Result <T> failed() {
        return restResult(CommonConstants.ERROR,null, CommonConstants.FAIL, null);
    }

    public static <T> Result <T> failed(String msg) {
        return restResult(CommonConstants.ERROR,null, CommonConstants.FAIL, msg);
    }

    public static <T> Result <T> failed(T data) {
        return restResult(CommonConstants.ERROR,data, CommonConstants.FAIL, null);
    }

    public static <T> Result <T> failed(T data, String msg) {
        return restResult(CommonConstants.ERROR,data, CommonConstants.FAIL, msg);
    }

    private static <T> Result <T> restResult(boolean flag, T data, int code, String message) {
        Result <T> result = new Result <>();
        result.setFlag(flag);
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(boolean flag, int code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
