package com.everday.useapp.network.interceptor;

import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.utils.PhoneUtils;
import com.everday.useapp.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/7/16
 * description: 设备拦截器
 */
public class DeviceInterceptor implements Interceptor {
    private Gson gson;
    private Map<String, Object> map;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String host = request.url().toString();
        //判断HOST是行测地址
        if (host.contains(Constants.HOSTONE)) {
            if (gson == null) {
                gson = new Gson();
                map = new HashMap<>();
            }
            map.put("rom", PhoneUtils.getTotalMemory());
            map.put("ram", PhoneUtils.getAvailMemory());
            map.put("ver", PhoneUtils.getSystemVersion());
            map.put("model", PhoneUtils.getPhoneModel());
            map.put("imei", PhoneUtils.getIMEI());
            map.put("brand", PhoneUtils.getBrand());
            map.put("sdk", PhoneUtils.getSDKVersion());
            map.put("av", PhoneUtils.getVersionCode());
            map.put("scs", PhoneUtils.getVersionCode());
            String info = gson.toJson(map);
            //获取唯一标识
            String uuid = (String) PreferencesUtils.get(UserConfig.UUID, "");
            if(uuid.isEmpty()){
                uuid = UUID.randomUUID().toString();
                PreferencesUtils.put(UserConfig.UUID,uuid,true);
            }
            request = request.newBuilder()
                    .addHeader("info", info)
                    .addHeader("PhoneUuid", uuid)
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
        return chain.proceed(request);
    }
}
