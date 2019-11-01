package com.everday.useapp.activity.login;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.everday.useapp.R;
import com.everday.useapp.UseApplication;
import com.everday.useapp.base.BaseActivity;
import com.everday.useapp.constants.API;
import com.everday.useapp.constants.Constants;
import com.everday.useapp.constants.UserConfig;
import com.everday.useapp.dialog.BamToast;
import com.everday.useapp.dialog.UpDateDialogFragment;
import com.everday.useapp.dialog.UseDialog;
import com.everday.useapp.entity.VersionInfoBean;
import com.everday.useapp.network.HttpManager;
import com.everday.useapp.utils.ActivityUtils;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/11
 * description: 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.iv_msg_setting)
    ImageView imageView;
    //grab值为1开启自动抢单2关闭
    private Integer grab = 1;
    private Boolean show;
    private int network;
    private UpDateDialogFragment appUpdate;
    private static final int INSTALL_PACKAGES_REQUESTCODE = 1000;
    private static final int WRITE_EXTERNAL_STORAGE = 1001;
    //授权完成后，开始安装
    private static final int GET_UNKNOWN_APP_SOURCES = 100;
    private NotificationCompat.Builder builder;
    private File mApkFile;
    private DownloadTask task;
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvTitle.setText("设置");
        ivMessage.setVisibility(View.GONE);
        String localVersionName = AppUtils.getLocalVersionName(UseApplication.getApplication());
        tvVersion.setText(localVersionName);
        show = (Boolean) PreferencesUtils.get(UserConfig.AUTOMATIC,false);
        imageView.setImageResource(show == true ?R.mipmap.icon_bt:R.mipmap.icon_uncheck);
    }

    @OnClick({R.id.tv_out_login,R.id.iv_msg_setting,R.id.tv_update,R.id.tv2})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_out_login:
                UseDialog.getInstance("是否确定要退出当前账号?", "确定", "取消").show(getSupportFragmentManager(), "usedialog");
                break;
            case R.id.iv_msg_setting:
                network = 1;
                automatic();
                break;
            case R.id.tv_update:
                network = 2;
                chekcVersion();
                break;
            case R.id.tv2:
                ActivityUtils.startActivity(this,AboutActivity.class);
                break;
        }
    }
    /**
     * 版本更新
     */
    public void chekcVersion() {
        String gson = "{\"version\":"+AppUtils.getLocalVersion()+"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.UPDATEBYVERSION, this, requestBody);
    }
    /**
     * 自动抢单
     */
    public void automatic(){
        if(show){
            grab = 2;
        }else{
            grab = 1;
        }
        String gson = "{ \"grab\":\""+grab+"\",\"tele\":\""+telePhone+"\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.CONTENTYPE),gson);
        HttpManager.getInstance().post(Constants.HOST+ API.AUTOMATIC,this,requestBody);
    }


    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        if(isFinishing()){return;}
        if(network == 1) {
            show = grab == 1 ? true : false;
            imageView.setImageResource(grab == 1 ? R.mipmap.icon_bt : R.mipmap.icon_uncheck);
            PreferencesUtils.put(UserConfig.AUTOMATIC, grab == 1 ? true : false, true);
        }else{
            VersionInfoBean versionInfoBean = GsonUtils.getInstance().parseJsonToBean(t, VersionInfoBean.class);
            if (versionInfoBean.getData().getIsUpdate() == 1) {
                showNewVersion(versionInfoBean.getData().getNote(), versionInfoBean.getData().getAndroid(), versionInfoBean.getData().getForce(),versionInfoBean.getData().getTitle());
            }else {
                BamToast.show("已经是最新版本了");
            }
        }
    }

    @Override
    public void onFailure(String message, int error) {
        super.onFailure(message, error);
    }

    @Override
    public void onThrows(String message, int error) {
        super.onThrows(message, error);
    }
    /**
     * app更新框
     *
     * @param versionno
     * @param fileaddress
     * @param isForbbiden
     */
    private void showNewVersion(String versionno, final String fileaddress, int isForbbiden,String versionName) {
        Bundle bundle = new Bundle();
        bundle.putString("versionName",versionName);
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1002);
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

        builder = new NotificationCompat.Builder(this, channelId);
        builder.setContentTitle("老吴公考更新Apk") //设置通知标题
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
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
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".fileprovider", mApkFile);
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
            Intent intent = new Intent(this, SettingActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(SettingActivity.this, 0, intent, 0);
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
            case WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chekcVersion();
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
