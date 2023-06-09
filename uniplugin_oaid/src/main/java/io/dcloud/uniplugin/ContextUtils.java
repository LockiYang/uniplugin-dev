package io.dcloud.uniplugin;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Method;

public class ContextUtils {
    /**
     * 获取当前应用的Application
     * 先使用ActivityThread里获取Application的方法，如果没有获取到，
     * 再使用AppGlobals里面的获取Application的方法
     * @return
     */
    public static Application getCurApplication(){
        Application application = null;
        try{
            Class atClass = Class.forName("android.app.ActivityThread");
            Method currentApplicationMethod = atClass.getDeclaredMethod("currentApplication");
            currentApplicationMethod.setAccessible(true);
            application = (Application) currentApplicationMethod.invoke(null);
            Log.d("fw_create","curApp class1:"+application);
        }catch (Exception e){
            Log.d("fw_create","e:"+e.toString());
        }

        if(application != null)
            return application;

        try{
            Class atClass = Class.forName("android.app.AppGlobals");
            Method currentApplicationMethod = atClass.getDeclaredMethod("getInitialApplication");
            currentApplicationMethod.setAccessible(true);
            application = (Application) currentApplicationMethod.invoke(null);
            Log.d("fw_create","curApp class2:"+application);
        }catch (Exception e){
            Log.d("fw_create","e:"+e.toString());
        }

        return application;
    }

}
