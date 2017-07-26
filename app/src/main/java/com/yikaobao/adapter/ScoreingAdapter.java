package com.yikaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.data.DataScoreing;
import com.yikaobao.data.DataSubmitScoreing;
import com.yikaobao.view.DialogScoreing;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//考站列表
public class ScoreingAdapter extends RecyclerView.Adapter<ScoreingAdapter.MyViewHolder> implements View.OnClickListener, DialogScoreing.getScore {


    /**
     * Created by Administrator on 2016/8/3.
     */
    private LinkedList<DataScoreing.DataBean.CateBean> mDatas = new LinkedList<DataScoreing.DataBean.CateBean>();

    //打分数据
    private LinkedList<DataSubmitScoreing.ScoreInfoBean> mSubmitDatas = new LinkedList<DataSubmitScoreing.ScoreInfoBean>();


    private Context mContext;
    private LayoutInflater inflater;
    private DialogScoreing dialogScoreing;
    //当前打分id
    private int position;

    public ScoreingAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据
     */
    public void addData(List<DataScoreing.DataBean.CateBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataScoreing.DataBean.CateBean datas) {
        mDatas.addLast(datas);
        notifyDataSetChanged();
    }

    public void changeData(DataScoreing.DataBean.CateBean data, int position) {
        mDatas.set(position, data);
        this.notifyItemChanged(position);
    }

    /**
     * 刷新数据
     */
    public void upData(List<DataScoreing.DataBean.CateBean> datas, List<DataScoreing.DataBean.PointsBean> datas2) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
        //初始化打分Dialog
        dialogScoreing = new DialogScoreing(mContext, R.style.Dialog);
        dialogScoreing.addDatas(datas2, this);

        //初始化分数容器
        for (int i = 0; i <datas.size(); i++) {
            DataSubmitScoreing.ScoreInfoBean scoreInfoBean = new DataSubmitScoreing.ScoreInfoBean();
            scoreInfoBean.setCase_assessmentId(datas.get(i).getId());
            mSubmitDatas.add(scoreInfoBean);
        }
    }

    //返回所有打分分数
    public LinkedList<DataSubmitScoreing.ScoreInfoBean> getDatas() {
        return mSubmitDatas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_scoreing_item, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemScoreItem.setText(position + 1 + " . " + mDatas.get(position).getName());
        holder.itemScoreItem.setOnClickListener(this);
        holder.itemScoreItem.setTag(position);

        holder.itemScoreParent.setText(mDatas.get(position).getButtonStr());

        holder.itemScoreParent.setOnClickListener(this);
        holder.itemScoreParent.setTag(position);
    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_score_dafeng:
                this.position = position;
                dialogScoreing.addData(mDatas.get(position).getId());
                dialogScoreing.show();
                break;
            case R.id.item_select_room_parent:

//                Intent intent = new Intent(mContext, CandidatesListActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("stationId", mDatas.get(position).getId());
//                intent.putExtra("state", mDatas.get(position).getState());
//                mContext.startActivity(intent);

                break;
        }
    }

    @Override
    public void onClick(int case_assessmentId, int score, String title) {
        //mSubmitDatas.get(position).setCase_assessmentId(case_assessmentId);
        mSubmitDatas.get(position).setResult(score);
        mDatas.get(position).setButtonStr(score + "分");
        this.notifyDataSetChanged();
        dialogScoreing.dismiss();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_score_item)
        TextView itemScoreItem;
        @Bind(R.id.item_score_dafeng)
        Button itemScoreParent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
