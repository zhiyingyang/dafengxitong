package com.yikaobao.tools;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yikaobao.R;
import com.yikaobao.activity.LoginActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.view.CustomizeDialog;

import org.greenrobot.eventbus.EventBus;

import static android.content.ContentValues.TAG;

/**
 * Created by lx on 2017/5/16.
 */

public class Tools {
    CustomizeDialog clear;

    public static void myMakeText(final Context context, String message) {
        if ("token验证失败".equals(message)) {

            SharedPMananger.remove(SharedPMananger.USER);
            BaseApplication.user = null;
            TextView textView = new TextView(context);
            textView.setText("您的账号已经在另一台设备登录,请重新登录");

            textView.setTextSize((float) (Tools.getWidth(context)*0.025));

            textView.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 50, 0, 50);
            textView.setLayoutParams(lp);

            CustomizeDialog clear = new CustomizeDialog.Builder(AppManager.getAppManager().currentActivity(), R.style.Dialog).setTitle("消息").setView(textView).setOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppManager.getAppManager().finishAllActivity();
                    Intent LaunchIntent = new Intent();
                    LaunchIntent.setClass(context, LoginActivity.class);
                    LaunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(LaunchIntent);
                }
            }).myCreate();
            clear.show();
            return;
        }

        //  if (BaseApplication.caseId == 0) return;
        if ("考案Id为空或格式有误".equals(message) || "输入考案Id有误".equals(message) || "考案Id有误".equals(message))
            return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

    /**
     * 对象转json
     */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * json转对象
     */
    public static <T> T toObject(String jsonString, Class<T> cls) {
        T t = null;
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
        try {
            t = new Gson().fromJson(jsonObject.toString(), cls);
        } catch (Exception e) {
        }
        return t;
    }


    /**
     * @param flag 发送通知
     */
    public static void sendEvent(int flag) {

        FavorEvent anyEventType = new FavorEvent();
        anyEventType.setId(flag);
        EventBus.getDefault().postSticky(anyEventType);

    }

    public static boolean isNull(String str, String message, Context context) {
        if (str == null || "".equals(str)) {
            Tools.myMakeText(context, message);
            return false;
        }
        return true;
    }

    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //隐藏系统软键盘

    public static void closeSoftKeybord( View mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

}
