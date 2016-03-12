package com.kdb.vo;

/**
 * @author xiliang.zxl
 * @date 2016-01-10 下午5:11
 */
public class Result<T> {

    private String code;
    private String message;
    private boolean success=true;
    private T data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(String code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Result(String code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
}
