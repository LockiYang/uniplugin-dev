package io.dcloud.uniplugin;

import android.app.Application;
import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

import com.github.gzuliyujiang.oaid.DeviceID;
import com.github.gzuliyujiang.oaid.IGetter;
//import com.github.gzuliyujiang.oaid.DeviceIdentifier;

public class OAIDModule extends UniModule {

    @UniJSMethod(uiThread = true)
    public void getOAIDAsync(final UniJSCallback callback) {
        Application context =  ContextUtils.getCurApplication();
        DeviceID.getOAID(context, new IGetter() {
            @Override
            public void onOAIDGetComplete(@NonNull String oaid) {
                if(callback != null) {
                    JSONObject data = new JSONObject();
                    data.put("oaid", oaid);
                    callback.invoke(data);
                }
            }

            @Override
            public void onOAIDGetError(Exception error) {
                if(callback != null) {
                    JSONObject data = new JSONObject();
                    data.put("oaid", "ooxx");
                    callback.invoke(data);
                }
            }
        });
    }

    //run JS thread
//    @UniJSMethod (uiThread = false)
//    public JSONObject getOAID(){
//        // 版本4实现
//        Application context =  ContextUtils.getCurApplication();
////        DeviceIdentifier.register(context);
//        String oaid = DeviceIdentifier.getOAID(context);
//        String imei = DeviceIdentifier.getIMEI(context);
//        // 获取安卓ID，可能为空
//        String androidID = DeviceIdentifier.getAndroidID(context);
//        // 获取数字版权管理ID，可能为空
//        String widevineID = DeviceIdentifier.getWidevineID();
//        // 获取伪造ID，根据硬件信息生成，不会为空，有大概率会重复
//        String pseudoID = DeviceIdentifier.getPseudoID();
//        // 获取GUID，随机生成，不会为空
//        String guid = DeviceIdentifier.getGUID(context);
//        // 是否支持OAID/AAID
//        boolean supported = DeviceID.supportedOAID(context);
//        JSONObject data = new JSONObject();
//        data.put("oaid", oaid);
//        return data;
//    }
}
