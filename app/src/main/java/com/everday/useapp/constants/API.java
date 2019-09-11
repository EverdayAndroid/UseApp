package com.everday.useapp.constants;
/**
 * date:2019/8/28
 * author:Everday
 * email wangtahandsome@gmail.com
 * desc: 接口地址
 */
public class API {
    public static String MYTASK = "getMyTask";
    //登录
    public static String LOGIN = "app/doLogin";
    //注册
    public static String REGISTER = "app/register";
    //获取验证码
    public static String SENDCODE = "app/sendCode";
    //忘记密码
    public static String FORGETPWD = "app/forgetPwd";
    //修改密码
    public static String UPDATEPASSWORD = "app/setting/updatePassword";
    //用户信息
    public static String USERDETAIL = "appuserApi/userDetail";
    //商户列表
    public static String MERCHANT = "shglApi/shlb";

    //查询个人头像
    public static String GETAVATAR = "app/setting/getAvatar";
    //修改个人头像
    public static String UPLOADAVATAR = "app/setting/uploadAvatar";
    //修改个人昵称
    public static String UPDATENICKNAME = "appuserApi/updateNickName";
    //待接单分页查询
    public static String GETTASKDJD = "taskApi/getTaskdjd";
    //接单按钮接口
    public static String TAKETASK = "taskApi/takeTask";
    //我的任务列表
    public static String GETMYTASK = "taskApi/getMyTask";
    //app版本升级
    public static String UPDATEBYVERSION = "appupdate/updateByVersion";
    //我的消息
    public static String NOTICE = "noticeApi/notice";
    //退出
    public static String OUTLOGIN = "app/logout";
}
