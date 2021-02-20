package com.ww.lib_common

import android.app.Application;
import java.lang.reflect.InvocationTargetException

/**
 * 获取全局的Application
 * 对于组件化项目,不可能把项目实际的Application下沉到Base,而且各个module也不需要知道Application真实名字
 * 这种一次反射就能获取全局Application对象的方式相比于在Application#OnCreate保存一份的方式更加通用
 */
object AppGlobals {
    private var sApplication: Application? = null

    fun getApplication(): Application? {
        if (sApplication == null) {
            try {
                sApplication = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
        return sApplication
    }
}