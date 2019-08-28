package com.everday.useapp.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc 数据转换
**/
public class GsonConverterFactory<T> implements Converter<ResponseBody,T> {
    private Type type;
    private Gson gson;
    public GsonConverterFactory(Gson gson, Type type){
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String body = value.string();
        return gson.fromJson(body,type);
    }
}
