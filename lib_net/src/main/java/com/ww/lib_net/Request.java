package com.ww.lib_net;

import androidx.annotation.IntDef;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author ww
 * @date 2021/3/2.
 * description：
 */
public abstract class Request<T, R> {
    private String mURl;
    protected HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, Object> params = new HashMap<>();

    //仅仅只访问本地缓存，即便本地不存在，也不会发起网络请求
    public static final int CACHE_ONLY = 1;
    //先访问缓存， 同时发起网络的请求，成功后缓存到本地
    public static final int CACHE_FIRST = 2;
    //仅仅只访问服务器，不访问任何缓存
    public static final int NET_OMLY = 3;
    //先访问网络，成功后混存到本地
    public static final int NET_CACHE = 4;

    @IntDef({CACHE_ONLY,CACHE_FIRST,NET_OMLY,NET_CACHE})
    public @interface CacheStrategy{

    }

    public Request(String mURl) {
        this.mURl = mURl;
    }

    public R addHeader(String key,String value){
        headers.put(key,value);
        return (R) this;
    }

    public R addParams(String key,Object value){
        try {
            Field field = value.getClass().getField("TYPE");
            Class claz = (Class) field.get(null);
            if(claz.isPrimitive()){

            }
            params.put(key,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (R) this;
    }
}
