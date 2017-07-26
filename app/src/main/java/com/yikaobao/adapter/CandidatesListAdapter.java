package com.yikaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.tools.Tools;
import com.yikaobao.activity.ScoreActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.data.DataStudentsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//考試項目
public class CandidatesListAdapter extends RecyclerView.Adapter<CandidatesListAdapter.MyViewHolder> implements View.OnClickListener {


    /**
     * Created by Administrator on 2016/8/3.
     */
    private ArrayList<DataStudentsList.DataBean.UserInfoBean> mDatas = new ArrayList<DataStudentsList.DataBean.UserInfoBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private DataStudentsList.DataBean.StationInfoBean caseInfoBean;

    public CandidatesListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    /**
     * 添加数据
     */
    public void addData(List<DataStudentsList.DataBean.UserInfoBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataStudentsList.DataBean.UserInfoBean datas) {
        //mDatas.addLast(datas);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }


    public void changeData(DataStudentsList.DataBean.UserInfoBean data, int position) {
        mDatas.set(position, data);
        this.notifyItemChanged(position);
    }

    /**
     * 刷新数据
     */
    public void upData(List<DataStudentsList.DataBean.UserInfoBean> datas, DataStudentsList.DataBean.StationInfoBean caseInfoBean) {
        mDatas.clear();
        mDatas.addAll(datas);
        this.caseInfoBean = caseInfoBean;
        notifyDataSetChanged();
    }

    public ArrayList<DataStudentsList.DataBean.UserInfoBean> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViqewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = inflater.inflate(R.layout.item_candidates, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemScoreItem.setText(mDatas.get(position).getUsername());
        holder.itemScoreEv.setText(mDatas.get(position).getTotal_score() > -1 ? mDatas.get(position).getTotal_score() + "" : mDatas.get(position).getEmployeeNumber());

        holder.cardView.setOnClickListener(this);
        holder.cardView.setTag(position);

    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.card_view:
                //显示分数
                if (mDatas.get(position).getTotal_score() > -1) {
                    Tools.myMakeText(mContext, "此次考试已经评分完成");
                    return;
                }
                //是否是考官
                if (caseInfoBean.getExamier() != BaseApplication.user.getData().getId()) {
                    Tools.myMakeText(mContext, "对不起,您不是本场考试的考官");
                    return;
                }


                Intent intent = new Intent(mContext, ScoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle bundle = new Bundle();
//
//                bundle.putSerializable("params", caseInfoBean);
//                bundle.putSerializable("UserInfoBean", mDatas.get(position));
                intent.putExtra("position", position);
                intent.putExtra("name", mDatas.get(position).getUsername());
                intent.putExtra("stationId", caseInfoBean.getId());
                intent.putExtra("studentId", mDatas.get(position).getId());

                mContext.startActivity(intent);
                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_score_item)
        TextView itemScoreItem;
        @Bind(R.id.item_score_ev)
        TextView itemScoreEv;
        @Bind(R.id.card_view)
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
