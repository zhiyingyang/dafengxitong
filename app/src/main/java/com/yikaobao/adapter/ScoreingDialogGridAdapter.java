package com.yikaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yikaobao.R;
import com.yikaobao.tools.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//
public class ScoreingDialogGridAdapter extends RecyclerView.Adapter<ScoreingDialogGridAdapter.MyViewHolder> {


    /**
     * Created by Administrator on 2016/8/3.
     */
    //原始数据
    private List<String> mDatas = new ArrayList<String>();

    private Context mContext;
    private LayoutInflater inflater;
    //position上次选择的id
    private int position;

    private View.OnClickListener onClickListener;


    public ScoreingDialogGridAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据
     */
    public void addData(List<String> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    //选择某项
    public void select() {
    }


//    public void changeData(DataScoreing.DataBean.PointsBean data, int position) {
//        mDatas.set(position, data);
//        this.notifyItemChanged(position);
//    }

    /**
     * 刷新数据
     */
    public void upData(List<String> datas) {

        mDatas.clear();
        mDatas.addAll(datas);
        position = 0;
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_dalog_grid_list, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemDialogTv.setText(mDatas.get(position));
        //  float size = (float) (Tools.getWidth(mContext) * 0.5);
        //   holder.itemDialogTv.setTextSize(size);
        ViewGroup.LayoutParams params = holder.itemDialogTv.getLayoutParams();
 //       params.width = (int) (Tools.getWidth(mContext) * 0.18);
        params.height = (int) (Tools.getWidth(mContext) * 0.09);
//        holder.itemDialogTv.setLayoutParams(params);

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_dialog_bu)
        Button itemDialogTv;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            if (onClickListener != null) {
                itemDialogTv.setOnClickListener(onClickListener);
            }
        }
    }


}
