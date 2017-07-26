package com.yikaobao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yikaobao.data.DataUser;
import com.yikaobao.tools.AppManager;

import org.greenrobot.eventbus.EventBus;

import static com.yikaobao.base.BaseApplication.caseId;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            BaseApplication.user = (DataUser) savedInstanceState.getSerializable("UserData");
            BaseApplication.caseId = savedInstanceState.getInt("caseId");
        }
        //将Activity实例添加到AppManager的堆栈
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("UserData", BaseApplication.user);
        outState.putInt("caseId", caseId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity实例从AppManager的堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
    }
}
