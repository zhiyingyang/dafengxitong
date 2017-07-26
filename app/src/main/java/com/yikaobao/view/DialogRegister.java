package com.yikaobao.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.adapter.DialogListAdapter;
import com.yikaobao.tools.Tools;

import java.util.List;

public class DialogRegister extends AlertDialog implements View.OnClickListener {

    TextView dialogTitle;
    RecyclerView dialogRv;
    TextView dialogBu;
    private Context context;
    DialogListAdapter dialogListAdapter;
    private getData getData;
    private String title;

    protected DialogRegister(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public DialogRegister(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected DialogRegister(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        if (dialogListAdapter.getHospitalInfoBean().getId()==Integer.MIN_VALUE){
        Tools.myMakeText(context,"请选择整体评价");
        return;
        }

        getData.getData(dialogListAdapter.getHospitalInfoBean().getName(), dialogListAdapter.getHospitalInfoBean().getId());
        dismiss();
    }

    public interface getData {
        //选择的字符串 id
        void getData(String string, int id);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window dialogWindow = getWindow();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (dm.widthPixels * 0.9);

        dialogWindow.setAttributes(p);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_list, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        dialogRv = (RecyclerView) layout.findViewById(R.id.dialog_rv);
        dialogBu = (TextView) layout.findViewById(R.id.dialog_bu);
        dialogTitle = (TextView) layout.findViewById(R.id.dialog_title);
        //设置布局管理器
        dialogRv.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        dialogRv.setLayoutManager(layoutManager);
        //设置Adapter
        dialogRv.setAdapter(dialogListAdapter);
        dialogBu.setOnClickListener(this);
        dialogTitle.setText(title);
        this.setContentView(layout);
    }

    public DialogRegister addDatas(List<DialogListAdapter.HospitalInfoBean> hospitalInfoBeen, String title, getData getData) {
        this.title = title;
        dialogListAdapter = new DialogListAdapter(context.getApplicationContext());
        dialogListAdapter.addData(hospitalInfoBeen);
        this.getData = getData;
        return this;
    }

}
