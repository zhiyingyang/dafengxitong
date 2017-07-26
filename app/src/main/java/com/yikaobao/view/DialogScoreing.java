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

import com.yikaobao.R;
import com.yikaobao.adapter.ScoreingDialogAdapter;
import com.yikaobao.data.DataScoreing;

import java.util.List;


public class DialogScoreing extends AlertDialog implements View.OnClickListener {

    private Context context;
    ScoreingDialogAdapter scoreingDialogAdapter;
    List<DataScoreing.DataBean.PointsBean> datas;
    //当前考提题id
    private int testId;
    private getScore getScore;

    protected DialogScoreing(@NonNull Context context) {
        super(context);
    }

    public DialogScoreing(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected DialogScoreing(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_scoreing, null);
        Window dialogWindow = getWindow();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (dm.widthPixels * 0.9);
        // p.height = (int) (dm.heightPixels * 0.9);
        dialogWindow.setAttributes(p);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.dialog_scoreing_rv);
        layout.findViewById(R.id.dialog_bu).setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        scoreingDialogAdapter = new ScoreingDialogAdapter(context);
        scoreingDialogAdapter.setGetScore(getScore,testId);
        //设置Adapter
        recyclerView.setAdapter(scoreingDialogAdapter);
        scoreingDialogAdapter.upData(datas);
        this.setContentView(layout);

    }

    public void addDatas(List<DataScoreing.DataBean.PointsBean> datas, DialogScoreing.getScore getScore) {
        this.datas = datas;
        this.getScore = getScore;

    }

    public void addData(int testId) {
        this.testId = testId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_bu:

                this.getScore.onClick(testId, scoreingDialogAdapter.getData(), scoreingDialogAdapter.getTitleId());

                this.dismiss();
                break;
        }

    }

    public interface getScore {
        public void onClick(int case_assessmentId, int score, String title);
    }

    @Override
    public void show() {
        super.show();
        scoreingDialogAdapter.resetData();
    }
}
