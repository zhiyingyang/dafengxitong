package com.yikaobao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.yikaobao.data.DataUser;
import com.yikaobao.tools.AppManager;
import com.yikaobao.tools.Tools;

import org.greenrobot.eventbus.EventBus;

import static com.yikaobao.base.BaseApplication.caseId;


public class BaseActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            BaseApplication.user = (DataUser) savedInstanceState.getSerializable("UserData");
            BaseApplication.caseId = savedInstanceState.getInt("caseId");
        }
        //将Activity实例添加到AppManager的堆栈   0402
        AppManager.getAppManager().addActivity(this);
       getWindow().getDecorView().setOnTouchListener(this);
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Tools.closeSoftKeybord(view,getApplicationContext());
        return false;
    }
}
