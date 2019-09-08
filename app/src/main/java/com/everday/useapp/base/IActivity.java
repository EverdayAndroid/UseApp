package com.everday.useapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

public interface IActivity {
    /**
     * 是否使用 EventBus
     * @return 返回 {@code true},会自动注册 EventBus
     */
    boolean useEventBus();
    /**
     * 初始化 View, 如果 {@link #initView(Bundle)} 返回 0, 框架则不会调用 {@link Activity#setContentView(int)}
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);

    /**
     * 状态栏适配
     */
    void initStatus();

    /**
     * 状态栏背景
     * @return
     */
    int initStatusColor();
}
