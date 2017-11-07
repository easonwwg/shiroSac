package com.sac.pojo;

/**
 * Created by 99079 on 2017/11/7.
 */
public class BaseResult<T> {

    /**
     * 是否成功标志
     */
    private boolean success;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String error;

    public BaseResult() {
    }


    public BaseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BaseResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "JsonResult [success=" + success + ", data=" + data + ", error=" + error + "]";
    }
}
