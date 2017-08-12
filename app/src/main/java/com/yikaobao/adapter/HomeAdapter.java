package com.yikaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.activity.KaoZhanListActivity;
import com.yikaobao.activity.QuestionnaireActivity;
import com.yikaobao.activity.QuestionnaireViewPagerActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.data.DataHomeTest;
import com.yikaobao.view.percentsuppor.PercentRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener {

    /**
     * Created by Administrator on 2016/8/3.
     */
    private List<DataHomeTest.DataBean> mDatas = new ArrayList<DataHomeTest.DataBean>();
    private Context mContext;
    private LayoutInflater inflater;

    public HomeAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据
     */
    public void addData(List<DataHomeTest.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void upData(List<DataHomeTest.DataBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }


    /**
     * 删除数据
     */
    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_test_case, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.homeParent.setTag(position);
        String string = null;
//        switch (mDatas.get(position).getText_state()) {
//            case 0:
//                string = "申请中";
//                break;
//            case 1:
//                string = "已通过";
//                break;
//            case 2:
//                string = "已完成";
//                break;
//            case 3:
//                string = "未通过";
//                break;
//            default:
//                string = "未知";
//        }

        holder.itemHomeSta.setText(string);
        holder.itemHomeName.setText(mDatas.get(position).getName());
        holder.homeParent.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        BaseApplication.caseId = mDatas.get(position).getId();

        Intent intent;
        if (BaseApplication.user.getData().getRoleId() == 3) {
            intent = new Intent(mContext, QuestionnaireViewPagerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            return;
        }
        intent = new Intent(mContext, KaoZhanListActivity.class);
        intent.putExtra("title", mDatas.get(position).getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_home_name)
        TextView itemHomeName;
        @Bind(R.id.item_home_sta)
        TextView itemHomeSta;
        @Bind(R.id.home_parent)
        PercentRelativeLayout homeParent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}
