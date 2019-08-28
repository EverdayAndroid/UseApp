package com.everday.useapp.base;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.mvp.IPresenter;
import com.everday.useapp.network.Network;
import com.everday.useapp.network.RetrofitClient;
import com.everday.useapp.network.RetrofitClientOne;
import com.everday.useapp.network.RetrofitClientTwo;
import com.everday.useapp.utils.PreferencesUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/6/29
 * description: 描述
 */
public class BasePresenter implements IPresenter {
    protected LifecycleProvider<ActivityEvent> provider;
    protected LifecycleProvider<FragmentEvent> providerFragment;
    protected Retrofit retrofit;
    protected String userName;
    protected String password;
    protected String token;
    protected Disposable baseDisposable;
    protected String SUCCESS = "200";
    public BasePresenter(LifecycleProvider<ActivityEvent> provider){
        retrofit = RetrofitClient.getInstance().getmRetrofit();
        this.provider = provider;
        token = (String) PreferencesUtils.get(UserConfig.TOKEN,"");
        userName = (String) PreferencesUtils.get(UserConfig.USERNAME,"");
        password = (String) PreferencesUtils.get(UserConfig.PASSWORD,"");
    }

    /**
     *
     * @param provider
     * @param network
     */
    public BasePresenter(LifecycleProvider<FragmentEvent> provider, Network network){
        if(network == Network.HOSTONE) {
            //行测地址
            retrofit = RetrofitClientOne.getInstance().getmRetrofit();
        }else {
            //申论地址
            retrofit = RetrofitClientTwo.getInstance().getmRetrofit();
        }
        this.providerFragment = provider;
        token = (String) PreferencesUtils.get(UserConfig.TOKEN,"");
        userName = (String) PreferencesUtils.get(UserConfig.USERNAME,"");
        password = (String) PreferencesUtils.get(UserConfig.PASSWORD,"");
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
