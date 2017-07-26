package com.yikaobao.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.view.percentsuppor.PercentLinearLayout;

public class CustomizeDialog extends AlertDialog {


    private TextView dialogTitle;
    private Context context;
    private String title;
    private View.OnClickListener onClickListener;
    private View mView;

    protected CustomizeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomizeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected CustomizeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public interface getData {
        //选择的字符串 id
        void getData(String string, int id);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        //对话框宽度
        Window dialogWindow = getWindow();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (dm.widthPixels * 0.9);
        dialogWindow.setAttributes(p);
        //解决软键盘不能弹出问题
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        //获取view
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_customize, null);

        TextView dialogTitle = (TextView) layout.findViewById(R.id.dialog_title);
        TextView dialogBu = (TextView) layout.findViewById(R.id.dialog_bu);
        PercentLinearLayout dialog_content = (PercentLinearLayout) layout.findViewById(R.id.dialog_content);
        dialog_content.addView(mView);
        dialogBu.setOnClickListener(onClickListener);
        dialogTitle.setText(title);
        this.setContentView(layout);

    }

    public static class Builder {
        private CustomizeDialog P;

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            P = new CustomizeDialog(context, themeResId);
        }

        public Builder setView(View view) {
            P.mView = view;
            return this;
        }

        public Builder setTitle(String string) {
            P.title = string;
            return this;
        }

        public Builder setOnClick(View.OnClickListener onClick) {
            P.onClickListener = onClick;
            return this;
        }

        public CustomizeDialog myCreate() {
            return P;
        }
    }


}
