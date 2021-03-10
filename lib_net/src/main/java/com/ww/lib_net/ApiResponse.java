package com.ww.lib_net;

/**
 * @author ww
 * @date 2021/3/9.
 * description：
 */
public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
