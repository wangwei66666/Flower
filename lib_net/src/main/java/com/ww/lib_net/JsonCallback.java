package com.ww.lib_net;

/**
 * @author ww
 * @date 2021/3/9.
 * description：
 */
public abstract class JsonCallback<T> {

    public void onSuccess(ApiResponse<T> response){}

    public void onError(ApiResponse<T> response){}

    public void onCacheSuccess(){}

}
