package com.yikaobao.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.data.DataScoreing;
import com.yikaobao.view.DialogScoreing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//滑动的大风
public class ScoreingDialogAdapter extends RecyclerView.Adapter<ScoreingDialogAdapter.MyViewHolder> implements View.OnClickListener {


    /**
     * Created by Administrator on 2016/8/3.
     */
    //原始数据
    private LinkedList<DataScoreing.DataBean.PointsBean> mDatas = new LinkedList<DataScoreing.DataBean.PointsBean>();

    private Context mContext;
    private LayoutInflater inflater;
    //position上次选择的id
    private int position;
    //当前考提题id
    private int testId;
    private DialogScoreing.getScore getScore;

    public ScoreingDialogAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    /**
     * 添加数据
     */
    public void addData(List<DataScoreing.DataBean.PointsBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataScoreing.DataBean.PointsBean datas) {
        mDatas.addLast(datas);
        notifyDataSetChanged();
    }


    public void changeData(DataScoreing.DataBean.PointsBean data, int position) {
        mDatas.set(position, data);
        this.notifyItemChanged(position);
    }


    /**
     * 刷新数据
     */
    public void upData(List<DataScoreing.DataBean.PointsBean> datas) {

        mDatas.clear();
        mDatas.addAll(datas);
        position = 0;
        notifyDataSetChanged();
    }

    //重置
    public void resetData() {
        mDatas.get(position).setIndex(0);
        notifyItemChanged(position);
        position = 0;
    }

//    //返回当前打分
//    public LinkedList<DataScoreing.DataBean.PointsBean> getDatas() {
//        return mDatas;
//    }

    //返回当前打分
    public int getData() {
        return mDatas.get(position).getMin() + mDatas.get(position).getIndex();
    }

    //返回当前标题
    public String getTitleId() {
        return mDatas.get(position).getDescribe();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_dialog_scoreing, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemDialogTitle.setText(mDatas.get(position).getDescribe());
        List<String> datas = new ArrayList<String>();
        for (int i = mDatas.get(position).getMin(); i <= mDatas.get(position).getMax(); i++) {
            datas.add(i + "");
        }

        GridLayoutManager layoutManager = new GridLayoutManager(ScoreingDialogAdapter.this.mContext, 4);
        //设置布局管理器
        holder.itemDialogRv.setHasFixedSize(true);
        holder.itemDialogRv.setLayoutManager(layoutManager);

        //设置布局管理器
        ScoreingDialogGridAdapter scoreingDialogGridAdapter = new ScoreingDialogGridAdapter(mContext);
        scoreingDialogGridAdapter.addData(datas);
        scoreingDialogGridAdapter.setOnClickListener(ScoreingDialogAdapter.this);
        holder.itemDialogRv.setAdapter(scoreingDialogGridAdapter);
//        TextView textView = new TextView(mContext);
//        textView.setText( "分");
//        textView.setTextSize((float) (Tools.getWidth(mContext) * 0.02));
//        textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlack));
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(0, 50, 0, 50);
//        holder.linearLayout.addView(textView);


//
//        holder.itemDialogWheel.setItems(datas);
//        holder.itemDialogWheel.setTag(position);
//        holder.itemDialogWheel.selectIndex(mDatas.get(position).getIndex());

    }

    @Override
    public void onClick(final View v) {
     //   final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_dialog_bu:
                Button button = (Button) v;

                getScore.onClick(testId, Integer.parseInt(button.getText().toString()), getTitleId());

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

    //设置监听器 和当前的题目testId
    public void setGetScore(DialogScoreing.getScore getScore, int testId) {
        this.getScore = getScore;
        this.testId = testId;
    }

//
//    @Override
//    public void onWheelItemChanged(WheelView wheelView, int i) {
//        mDatas.get((Integer) wheelView.getTag()).setIndex(i);
//
//        if (position == (Integer) wheelView.getTag()) return;
//        mDatas.get(position).setIndex(0);
//        notifyItemChanged(position);
//        position = (int) wheelView.getTag();
//
//    }
//
//    @Override
//    public void onWheelItemSelected(WheelView wheelView, int i) {
//
//    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_dialog_title)
        TextView itemDialogTitle;
        //@Bind(R.id.item_dialog_wheel)
        //WheelView itemDialogWheel;
        @Bind(R.id.item_dialog_rv)
        //LinearLayout linearLayout;
                RecyclerView itemDialogRv;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //设置为垂直布局，这也是默认的
            // layoutManager.setOrientation(OrientationHelper.VERTICAL);
            List<String> datas = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
                datas.add(i + "分");
            }


//            itemDialogWheel.setOnWheelItemSelectedListener(ScoreingDialogAdapter.this);
//            //反射修改属性
//            Class c = itemDialogWheel.getClass();
//            try {
//                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//                DisplayMetrics outMetrics = new DisplayMetrics();
//                wm.getDefaultDisplay().getMetrics(outMetrics);
//                Field f = c.getDeclaredField("mCenterTextSize");
//                f.setAccessible(true);
//                float size = (float) (outMetrics.widthPixels * 0.07);
//                f.set(itemDialogWheel, size);
//                Field f2 = c.getDeclaredField("mNormalTextSize");
//                f2.setAccessible(true);
//                size = (float) (outMetrics.widthPixels * 0.05);
//                f2.set(itemDialogWheel, size);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }
    }


}
