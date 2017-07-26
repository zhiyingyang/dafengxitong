package com.yikaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.activity.CandidatesListActivity;
import com.yikaobao.data.DataCaseList;
import com.yikaobao.view.percentsuppor.PercentLinearLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//考站列表
public class CaseListAdapter extends RecyclerView.Adapter<CaseListAdapter.MyViewHolder> implements View.OnClickListener {


    /**
     * Created by Administrator on 2016/8/3.
     */
    private LinkedList<DataCaseList.DataBean> mDatas = new LinkedList<DataCaseList.DataBean>();
    private Context mContext;
    private LayoutInflater inflater;

    public CaseListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据
     */
    public void addData(List<DataCaseList.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataCaseList.DataBean datas) {
        mDatas.addLast(datas);
        notifyDataSetChanged();
    }


    public void changeData(DataCaseList.DataBean data, int position) {
        mDatas.set(position, data);
        this.notifyItemChanged(position);
    }

    /**
     * 刷新数据
     */
    public void upData(List<DataCaseList.DataBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public LinkedList<DataCaseList.DataBean> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_score_item, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemScoreItem.setText(mDatas.get(position).getName());
//        if (mDatas.get(position).getExamier()== BaseApplication.user.getData().getId()){
//            holder.itemScoreEv.setText("考官");
//        }else{
            holder.itemScoreEv.setText("");
 //       }

        holder.itemSelectRoomParent.setOnClickListener(this);
        holder.itemSelectRoomParent.setTag(position);



    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_select_room_parent:
                Intent intent = new Intent(mContext, CandidatesListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("stationId", mDatas.get(position).getId());
                intent.putExtra("state", mDatas.get(position).getState());
                intent.putExtra("title", mDatas.get(position).getName());
                mContext.startActivity(intent);

                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_score_item)
        TextView itemScoreItem;
        @Bind(R.id.item_score_ev)
        TextView itemScoreEv;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
