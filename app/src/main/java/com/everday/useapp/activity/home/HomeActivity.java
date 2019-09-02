package com.everday.useapp.activity.home;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.everday.useapp.R;
import com.everday.useapp.activity.home.fragment.HomeFragment;
import com.everday.useapp.activity.home.fragment.MineFragment;
import com.everday.useapp.activity.home.fragment.TaskFragment;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.UpDateDialogFragment;
import com.everday.useapp.entity.VersionInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.network.http.CallBack;
import com.everday.useapp.utils.AppSetting;
import com.everday.useapp.utils.AppUtils;
import com.everday.useapp.utils.GsonUtils;
import com.everday.useapp.utils.NotificationUtils;
import com.everday.useapp.utils.PreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/27
 * description: 首页
 */
public class HomeActivity extends BaseActivity  {
    private static final int INSTALL_PACKAGES_REQUESTCODE = 1000;
    //授权完成后，开始安装
    private static final int GET_UNKNOWN_APP_SOURCES = 100;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_mission)
    RadioButton radioMission;
    //    @BindView(R.id.radio_money)
//    RadioButton radioMoney;
    @BindView(R.id.radio_mine)
    RadioButton radioMine;
//    @BindView(R.id.radio_main)
    private String[] menu = new String[]{"HomeFragment", "TaskFragment", "MineFragment"};
    private int current = 0;
    private FragmentManager fragmentManager;
    private UpDateDialogFragment appUpdate;
    private NotificationCompat.Builder builder;
    private File mApkFile;
    private DownloadTask task;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        //记录下次启动不走启动广告页
        PreferencesUtils.put(UserConfig.FIRST_START,false,true);
        showFragment();
        chekcVersion();
    }

    /**
     * 显示碎片
     */
    public void showFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < menu.length; i++) {
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(menu[i]);
            if (fragmentByTag != null && fragmentByTag.isAdded()) {
                fragmentTransaction.hide(fragmentByTag);
            }
        }
        Fragment fragment = fragmentManager.findFragmentByTag(menu[current]);
        if (fragment == null) {
            fragment = initFragment(current);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.index_main, fragment, menu[current]);
        }
        fragmentTransaction.commit();
    }

    /**
     * 实例化碎片
     *
     * @param current
     * @return
     */
    public Fragment initFragment(int current) {
        Fragment fragment = null;
        switch (current) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new TaskFragment();
                break;
            case 2:
                fragment = new MineFragment();
                break;
            default:
                break;

        }
        return fragment;
    }

    @OnClick({R.id.radio_home, R.id.radio_mission, R.id.radio_mine})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.radio_home:
                current = 0;
                radioHome.setChecked(true);
                radioMission.setChecked(false);
                radioMine.setChecked(false);
                break;
            case R.id.radio_mission:
                current = 1;
                radioHome.setChecked(false);
                radioMission.setChecked(true);
                radioMine.setChecked(false);
                break;
            case R.id.radio_mine:
                current = 2;
                radioHome.setChecked(false);
                radioMission.setChecked(false);
                radioMine.setChecked(true);
                break;
        }
        showFragment();
    }

    public void chekcVersion() {
        gotoDownloadNewVersion("http://oss.pgyer.com/8fa98b26b4911b78657c059cc9b68e2a.apk?auth_key=1567438030-db5f034c6e49c0270428c2114631c256-0-f0085d0b4e1dec950dfe7a97e1d70875&response-content-disposition=attachment%3B+filename%3Dapp-debug.apk");
        FormBody.Builder builder = new FormBody.Builder();
//        HttpManager.getInstance().get("http://oss.pgyer.com/8fa98b26b4911b78657c059cc9b68e2a.apk?auth_key=1567438030-db5f034c6e49c0270428c2114631c256-0-f0085d0b4e1dec950dfe7a97e1d70875&response-content-disposition=attachment%3B+filename%3Dapp-debug.apk", null, this);
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        VersionInfoBean versionInfoBean = GsonUtils.getInstance().parseJsonToBean(t, VersionInfoBean.class);
        if (AppUtils.getLocalVersion() < versionInfoBean.getData().getAppCode()) {
            showNewVersion(versionInfoBean.getData().getAppUpdateContent(), versionInfoBean.getData().getAppDownloadPaht(), versionInfoBean.getData().getForce());
        }
    }

    /**
     * app更新框
     *
     * @param versionno
     * @param fileaddress
     * @param isForbbiden
     */
    private void showNewVersion(String versionno, final String fileaddress, int isForbbiden) {
        Bundle bundle = new Bundle();
        bundle.putString("versionno", versionno);
        bundle.putInt("isForbbiden", isForbbiden);
        if (appUpdate == null) {
            appUpdate = new UpDateDialogFragment();
            appUpdate.setArguments(bundle);
            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
            //动画
            ft4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft4.add(appUpdate, "update");
            ft4.commitAllowingStateLoss();
            appUpdate.setOnCloseListener(new UpDateDialogFragment.OnCloseListener() {
                @Override
                public void onClose() {
                    appUpdate.dismiss();
                }

                @Override
                public void onDoIt() {
                    appUpdate.dismiss();
                    gotoDownloadNewVersion(fileaddress);
                }
            });
        }
    }

    private void gotoDownloadNewVersion(String fileaddress) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1002);
            return;
        }
        String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lwgk_examination_downloaded";
        File file = new File(mFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        Toast.makeText(this, "后台下载安装包，请稍后安装...", Toast.LENGTH_SHORT);
        //设置文件的保存位置，读取当前下载的数据
        OkDownload.getInstance().setFolder(mFilePath);
        //开始下载
        GetRequest<File> request = OkGo.<File>get(fileaddress);


        //写法二：从数据库中恢复
        Progress progress = DownloadManager.getInstance().get(fileaddress);
        if (progress != null) {
            task = OkDownload.restore(progress)//
                    .register(new LogDownloadListener("tag"));
            task.start();
        }

        //这里第一个参数是tag，代表下载任务的唯一标识，传任意字符串都行，需要保证唯一,我这里用url作为了tag
        if (task == null) {
            task = OkDownload.request(fileaddress, request)//
                    .save()//
                    .register(new LogDownloadListener("tag"));
            task.start();
        }

        if (Build.VERSION.SDK_INT >= 19) {
            if (!NotificationUtils.isNotificationEnabled()) {
                AppSetting.gotoNotificationSet();
            }
        }
        //初始化提示器
        initNotification();
    }


    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message,error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }

    /**
     * 初始化通知 initNotification
     */
    private Notification notification;
    private NotificationManager notificationManager;
    private int notificationId = 1;

    private void initNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Random random = new Random();
        String channelId = String.valueOf(random.nextInt());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
        }

        builder = new NotificationCompat.Builder(HomeActivity.this, channelId);
        builder.setContentTitle("老吴公考更新Apk") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(true)  //
                .setOngoing(true)     // 不可以删除
                .setContentText("正在下载更新“老吴公考")
                .setChannelId(channelId)
                .setProgress(100, 0, false);
        notification = builder.build();//构建通知对象
    }

    private class LogDownloadListener extends DownloadListener {
        public LogDownloadListener(String tag) {
            super(tag);
        }

        @Override
        public void onStart(Progress progress) {


        }

        @Override
        public void onProgress(Progress progress) {
            //            System.out.println("当前的进度是" + progress.fraction * 100 + "%");
            if (progress.filePath != null) {
                mApkFile = new File(progress.filePath);
            }
            updateNotify((int) (progress.fraction * 100));
        }

        @Override
        public void onError(Progress progress) {
            notifyError("下载App失败");
        }

        @Override
        public void onFinish(File file, Progress progress) {

            mApkFile = file;
            //开始升级Apk
            checkIsAndroidO();


        }

        @Override
        public void onRemove(Progress progress) {
            notifyError("下载App失败");
        }
    }

    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            /**
             * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
             */
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                //正常安装Apk
                installApk();
            } else {
                //请求安装未知应用来源的权限。引导用户去设置
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_PACKAGES_REQUESTCODE);
            }
        } else {
            //正常安装Apk
            installApk();
        }
    }

    private void installApk() {
        //安装应用的逻辑(写自己的就可以)
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是Android 7.0以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(HomeActivity.this, "com.lwgk.fileprovider", mApkFile);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(mApkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    /**
     * 进度条
     *
     * @param progress
     */
    private void updateNotify(int progress) {
        builder.setProgress(100, progress, false);
        builder.setContentText("下载进度" + " 「" + progress + "%」");
        if (progress != 100) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(HomeActivity.this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
        }
        notification = builder.build();
        notificationManager.notify(notificationId, notification);
    }

    /**
     * 通知下载更新过程中的错误信息
     *
     * @param errorMsg 错误信息
     */
    private void notifyError(String errorMsg) {
        builder.setContentTitle("更新出现错误");
        builder.setContentText(errorMsg);
        notification = builder.build();
        notificationManager.notify(notificationId, notification);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case INSTALL_PACKAGES_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installApk();
                } else {
                    //可以跳转到指定的界面设置权限
                    Uri packageURI = Uri.parse("package:" + getPackageName());
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(intent, GET_UNKNOWN_APP_SOURCES);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GET_UNKNOWN_APP_SOURCES) {
            checkIsAndroidO();
        }
    }
}
