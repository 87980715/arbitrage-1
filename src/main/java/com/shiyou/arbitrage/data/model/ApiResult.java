package com.shiyou.arbitrage.data.model;

/**
 * FileName: ApiResult
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/8
 */
public class ApiResult<T> {
    private Long timestamp;
    private Integer statusCode;
    private T data;

    public ApiResult(Integer statusCode, T data) {
        this.timestamp = System.currentTimeMillis();
        this.setStatusCode(statusCode);
        this.setData(data);
    }

    public ApiResult(Integer statusCode) {
        this(statusCode, null);
    }

    public ApiResult(T data) {
        this(200, data);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
