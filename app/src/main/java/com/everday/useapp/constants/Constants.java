package com.everday.useapp.constants;
/**
* data 2019/6/28
* author Everday
* email wangtaohandsome@gmail.com
* desc 网络配置
**/
public class Constants {
    public static int CACHESIZE = 10*1024*1024; //缓存文件夹大小
    public static int CONNECTTIMEOUT = 5000;  //设置链接超时
    public static int READTIMEOUT = 5000; //设置读取超时
    public static int WRITETIMEOUT = 5000; //设置写入超时
    public static int SUCCESS = 200; //状态吗
    public static int TOKEN_ERROR = 403; //token失效
    public static int NO_NET_WORK = 1000; //没有网络
    public static int THROWS_CODE = 5000; //异常码
    public static int BUSINESS_ERROR = 500; //业务失败状态码
    public static String CONTENTYPE= "application/json";
    public static String FORM_DATA= "multipart/form-data";

//    public static String HOST = "http://www.yongrenbao.co/";//http://lhyg.natapp1.cc/
    public static String HOST = "http://lhyg.natapp1.cc/";
//    public static String AVATAR = "http://www.yongrenbao.co/app/setting/getAvatar/";
    public static String AVATAR = "http://lhyg.natapp1.cc/app/setting/getAvatar/";
    //行测地址
    public static String HOSTONE = "https://www.932edu.com/xc_test/";
    //申论地址
    public static String HOSTTWO = "https://www.932edu.com/argumentation_test/";
//    public static String HOST = "https://zsjw.taiyuan.gov.cn/tyjws-app/";
    public static String DATA_BASE_NAME = "Everday";

    public static String APK = "http://static.932edu.com/static/files/app/app-release_v3.2.apk";
}
