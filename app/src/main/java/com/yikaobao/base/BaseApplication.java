package com.yikaobao.base;

import android.app.Application;

import com.yikaobao.tools.CrashHandler;
import com.yikaobao.tools.SharedPMananger;
import com.yikaobao.tools.Tools;
import com.yikaobao.data.DataUser;


/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class BaseApplication extends Application {
    //店铺列表 显示的类型
    public static String dianItem = "吃货点点";
    //    //最基本的加载图片的处理方法
//    public static DisplayImageOptions displayImageOptions;
    public static DataUser user;
    public static BaseApplication myApplication;
//    //当前登录的用户
//    public static DataUser user;
//    //当前位置
//    public static BDLocation bdLocation = new BDLocation();

    public BaseApplication getContext() {
        return myApplication;
    }

    //当前flag的状态码
    // public static int[] flag;
    //当前考试id
    public static int caseId = Integer.MIN_VALUE;

    @Override
    public void onCreate() {
        super.onCreate();



        myApplication = this;
        // flag = new int[]{0, 0, 0};
        //全局捕获异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), this);

        if (BaseApplication.user == null) {
            //恢复登陆信息
            try {
                BaseApplication.user = Tools.toObject(
                        SharedPMananger.getString(SharedPMananger.USER, ""),
                        DataUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(MyApplication.this);            // 初始化 JPush
//        //初始化图片加载
//        MyApplication.displayImageOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.pic_no_bg)
//                .showImageForEmptyUri(R.drawable.pic_no_bg)
//                .showImageOnFail(R.drawable.pic_no_bg).cacheInMemory(true)
//                .cacheOnDisk(true).considerExifParams(true).build();
//        initImageLoader();
//
//        //初始化百度地图
//        SDKInitializer.initialize(getApplicationContext());
//
//        //恢复登陆信息
//        try {
//            MyApplication.user = Tools.toObject(
//                    SharedPMananger.getString(SharedPMananger.USER, ""),
//                    DataUser.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 初始化ImageLoader
//     */
//    public void initImageLoader() {
//        //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(this);
//        //Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);

    }

}
