package com.ww.lib_net;

import androidx.annotation.IntDef;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * @author ww
 * @date 2021/3/2.
 * description：
 */
public abstract class Request<T, R> {
    protected String mUrl;
    protected HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, Object> params = new HashMap<>();

    //仅仅只访问本地缓存，即便本地不存在，也不会发起网络请求
    public static final int CACHE_ONLY = 1;
    //先访问缓存， 同时发起网络的请求，成功后缓存到本地
    public static final int CACHE_FIRST = 2;
    //仅仅只访问服务器，不访问任何缓存
    public static final int NET_ONLY = 3;
    //先访问网络，成功后混存到本地
    public static final int NET_CACHE = 4;
    private String cacheKey;
    private Type mType;
    //private Class mClaz;
    private int mCacheStrategy = NET_ONLY;

    @IntDef({CACHE_ONLY,CACHE_FIRST,NET_ONLY,NET_CACHE})
    public @interface CacheStrategy{

    }

    public Request(String mURl) {
        this.mUrl = mURl;
    }
    protected abstract okhttp3.Request generateRequest(okhttp3.Request.Builder builder);

    public R addHeader(String key,String value){
        headers.put(key,value);
        return (R) this;
    }

    public R addParams(String key,Object value){
        try {
            Field field = value.getClass().getField("TYPE");
            Class claz = (Class) field.get(null);
            if(claz.isPrimitive()){
                params.put(key,value);
            }
            params.put(key,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (R) this;
    }

    public R cacheKey(String key) {
        this.cacheKey = key;
        return (R) this;
    }

    public R cacheStrategy(@CacheStrategy int cacheStrategy) {
        mCacheStrategy = cacheStrategy;
        return (R) this;
    }

     public R responseType(Type type) {
        mType = type;
        return (R) this;
    }

    public R responseType(Class claz) {
        mType = claz;
        return (R) this;
    }

    public void excute(){
        getCall();
    }

    public void excute(JsonCallback<T> callback){

    }

    private Call getCall() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        addHeader(builder);
        okhttp3.Request request = generateRequest(builder);
        Call call = ApiService.okHttpClient.newCall(request);
        return call;
    }

    private void addHeader(okhttp3.Request.Builder builder) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(),entry.getValue());
        }
    }

}
