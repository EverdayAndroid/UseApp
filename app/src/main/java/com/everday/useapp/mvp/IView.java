package com.everday.useapp.mvp;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/6/29
 * description: 视图层
 */
public interface IView {
    /**
     * 显示Loading
     */
    void showProgressDialog();

    /**
     * 隐藏Loading
     */
    void hideProgressDialog();

    /**
     * 显示错误信息
     *
     * @param msg 错误信息
     */
    void showError(String msg);
}
