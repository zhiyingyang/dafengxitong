package com.yikaobao.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.data.AnswerBean;
import com.yikaobao.data.DataQuestionId;
import com.yikaobao.tools.Tools;
import com.yikaobao.view.MyArrayList;
import com.yikaobao.view.MyRadioGroup;
import com.yikaobao.view.mradio.CheckBox;
import com.yikaobao.view.mradio.RadioButton;
import com.yikaobao.view.percentsuppor.PercentLinearLayout;
import com.yikaobao.view.percentsuppor.PercentRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by lx on 2017/5/18.
 */
//考站列表
public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, MyRadioGroup.OnCheckedChangeListener {


    /**
     * Created by Administrator on 2016/8/3.
     */
    private LinkedList<DataQuestionId.DataBean.InfoBean> mDatas = new LinkedList<DataQuestionId.DataBean.InfoBean>();
    private Context mContext;
    private LayoutInflater inflater;

    private List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();

    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {

    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_DanXuan, ITEM_TYPE_DUOXuan, ITEM_TYPE_BIANJI, ITEM_TYPE_PAIXU
    }

    public QuestionAdapter(Context context) {

        this.mContext = context;
        inflater = LayoutInflater.from(mContext);

    }

    /**
     * 添加数据
     */
    public void addData(List<DataQuestionId.DataBean.InfoBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataQuestionId.DataBean.InfoBean datas) {
        mDatas.addLast(datas);
        notifyDataSetChanged();
    }


    /**
     * 刷新数据
     */
    public void upData(List<DataQuestionId.DataBean.InfoBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        //初始化 保存答案的数组
        for (int i = 0; i < datas.size(); i++) {
            AnswerBean answerBean = new AnswerBean();
            answerBean.setQuestionId(mDatas.get(i).getQuestionId());
            answerBean.setType(mDatas.get(i).getType());

            if (mDatas.get(i).getType() == 4) {
                List<String> list = new MyArrayList();
                for (DataQuestionId.DataBean.InfoBean.OptionBean optionBean : datas.get(i).getOption()) {
                    list.add(String.valueOf(optionBean.getOptionId()));
                }
                answerBean.setAnswer(list);
            }

            answerBeans.add(answerBean);
        }

        notifyDataSetChanged();
    }

    public List<AnswerBean> getDatas() {
        return answerBeans;
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {

        return mDatas.get(position).getType();

    }

    //获取字体大小
    public static int adjustFontSize(int screenWidth, double size) {
        //  screenWidth=screenWidth>screenHeight?screenWidth:screenHeight;
        /**
         * 1. 在视图的 onsizechanged里获取视图宽度，一般情况下默认宽度是320，所以计算一个缩放比率
         rate = (float) w/320   w是实际宽度
         2.然后在设置字体尺寸时 paint.setTextSize((int)(8*rate));   8是在分辨率宽为320 下需要设置的字体大小
         实际字体大小 = 默认字体大小 x  rate
         */
        int rate = (int) (size * (float) screenWidth / 320); //我自己测试这个倍数比较适合，当然你可以测试后再修改
        return rate < 15 ? 15 : rate; //字体太小也不好看的
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:

                final View view = inflater.inflate(R.layout.item_question_danxuan, null, false);
                viewHolder = new Danxuan(view);
                break;

            case 2:

                final View view2 = inflater.inflate(R.layout.item_question_duoxuan, null, false);
                viewHolder = new DuoXuan(view2);
                break;

            case 3:

                final View view3 = inflater.inflate(R.layout.item_question_txt, null, false);
                viewHolder = new tx(view3);
                break;
            case 4:

                final View view4 = inflater.inflate(R.layout.item_question_paixu, null, false);
                viewHolder = new PaiXu(view4);
                break;

        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDatas.get(position).getType()) {

            case 1:

                Danxuan danxuan = (Danxuan) holder;
                danxuan.itemQusetionItem.setText(position + 1 + ".  " + mDatas.get(position).getTitle() + " [单选题]");
                danxuan.qusetionOption.removeAllViews();
                danxuan.qusetionOption.setTag(position);
                danxuan.itemQusetionItem.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6.5));
//                TextPaint tp =danxuan.itemQusetionItem.getPaint();
//                tp.setFakeBoldText(true);

                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                for (int i = 0; i < mDatas.get(position).getOption().size(); i++) {
                    RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.my_radio, null);
                    radioButton.setId(i);
                    radioButton.setText(mDatas.get(position).getOption().get(i).getOptionName());
                    danxuan.qusetionOption.addView(radioButton);
                    radioButton.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6));
                    radioButton.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                    for (String answer : answerBeans.get(position).getAnswer()) {
                        if (mDatas.get(position).getOption().get(i).getOptionId() == Integer.parseInt(answer)) {
                            radioButton.setChecked(true);
                        }
                    }
                }


                break;
            case 2:
                LayoutInflater inflaterC = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                DuoXuan duoXuan = (DuoXuan) holder;
                duoXuan.itemQusetionItem.setText(position + 1 + ".  " + mDatas.get(position).getTitle() + " [多选题]");
                duoXuan.qusetionOption.removeAllViews();
                duoXuan.qusetionOption.setTag(position);
                duoXuan.itemQusetionItem.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6.5));
                //  duoXuan.itemQusetionItem.getPaint().setFakeBoldText(true);


                for (int i = 0; i < mDatas.get(position).getOption().size(); i++) {

                    CheckBox radioButton = (CheckBox) inflaterC.inflate(R.layout.my_checkbox, null);
                    radioButton.setTag(mDatas.get(position).getOption().get(i).getOptionId());
                    radioButton.setId(i);
                    radioButton.setText(mDatas.get(position).getOption().get(i).getOptionName());
                    duoXuan.qusetionOption.addView(radioButton);

                    radioButton.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6));
                    radioButton.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                    radioButton.setOnCheckedChangeListener(duoXuan);

                    for (String answer : answerBeans.get(position).getAnswer()) {
                        if (mDatas.get(position).getOption().get(i).getOptionId() == Integer.parseInt(answer)) {
                            radioButton.setChecked(true);
                        }
                    }

                }

                break;
            case 3:

                tx tx = (QuestionAdapter.tx) holder;
                tx.itemQusetionItem.setText(position + 1 + ".  " + mDatas.get(position).getTitle() + " [编辑题]");
                tx.itemQusetionItem.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6.5));
                //  tx.itemQusetionItem.getPaint().setFakeBoldText(true);
                tx.editText.setTag(position);
                tx.editText.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6));
                tx.editText.setText(answerBeans.get(position).getAnswer().get(0));

                break;
            case 4:

                PaiXu paiXu = (PaiXu) holder;
                paiXu.itemQusetionItem.setText(position + 1 + ".  " + mDatas.get(position).getTitle() + " [排序题]");
                paiXu.itemQusetionItem.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6.5));
                //   paiXu.itemQusetionItem.getPaint().setFakeBoldText(true);

                ScoreItemAdapter scoreItemAdapter = new ScoreItemAdapter(mContext);

                scoreItemAdapter.upData(mDatas.get(position).getOption());

                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

                //设置布局管理器
                paiXu.itemQusetionPxRv.setHasFixedSize(true);
                //设置为垂直布局，这也是默认的
                layoutManager.setOrientation(OrientationHelper.VERTICAL);
                paiXu.itemQusetionPxRv.setLayoutManager(layoutManager);
                paiXu.itemQusetionPxRv.setAdapter(scoreItemAdapter);
                scoreItemAdapter.setQusetionId(position);
                //paiXu.scoreItemAdapter=scoreItemAdapter;

                break;
        }
    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_select_room_parent:


                break;
        }
    }

    class Danxuan extends RecyclerView.ViewHolder implements MyRadioGroup.OnCheckedChangeListener {

        @Bind(R.id.item_qusetion_item)
        TextView itemQusetionItem;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;
        @Bind(R.id.qusetion_option)
        com.yikaobao.view.MyRadioGroup qusetionOption;

        public Danxuan(View view) {
            super(view);
            ButterKnife.bind(this, view);
            qusetionOption.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(MyRadioGroup group, int checkedId) {
            int position = (int) group.getTag();
            answerBeans.get(position).getAnswer().clear();
            answerBeans.get(position).getAnswer().add(String.valueOf(mDatas.get(position).getOption().get(checkedId).getOptionId()));
        }

    }


    class DuoXuan extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        @Bind(R.id.qusetion_option)
        PercentLinearLayout qusetionOption;
        @Bind(R.id.item_qusetion_item)
        TextView itemQusetionItem;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;

        public DuoXuan(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int position = (int) ((ViewGroup) compoundButton.getParent()).getTag();
            if (b) {
                answerBeans.get(position).getAnswer().add(String.valueOf(compoundButton.getTag()));
            } else {
                answerBeans.get(position).getAnswer().remove(String.valueOf(compoundButton.getTag()));
            }
        }
    }


    class tx extends RecyclerView.ViewHolder implements TextWatcher {
        @Bind(R.id.item_qusetion_item)
        TextView itemQusetionItem;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;
        @Bind(R.id.item_qusetion_et)
        EditText editText;

        public tx(View view) {
            super(view);
            ButterKnife.bind(this, view);
            editText.addTextChangedListener(this);
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            int position = (int) editText.getTag();
            answerBeans.get(position).getAnswer().clear();
            answerBeans.get(position).getAnswer().add(editText.getText().toString());
        }
    }

    class PaiXu extends RecyclerView.ViewHolder {

        @Bind(R.id.item_qusetion_item)
        TextView itemQusetionItem;
        @Bind(R.id.item_qusetion_px_rv)
        RecyclerView itemQusetionPxRv;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;

        public PaiXu(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Created by lx on 2017/5/18.
     */
    //排序
    public class ScoreItemAdapter extends RecyclerView.Adapter<ScoreItemAdapter.MyViewHolder> implements View.OnClickListener, RecycleItemTouchHelper.ItemTouchHelperCallback {

        /**
         * Created by Administrator on 2016/8/3.
         */

        private LinkedList<DataQuestionId.DataBean.InfoBean.OptionBean> mDatas = new LinkedList<DataQuestionId.DataBean.InfoBean.OptionBean>();

        private Context mContext;

        private LayoutInflater inflater;

        //当前的题目id
        private int qusetionId;

        public ScoreItemAdapter(Context context) {

            this.mContext = context;

            inflater = LayoutInflater.from(mContext);

        }

        /**
         * 添加数据
         */
        public void addData(List<DataQuestionId.DataBean.InfoBean.OptionBean> datas) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }


        /**
         * 刷新数据
         */
        public void upData(List<DataQuestionId.DataBean.InfoBean.OptionBean> datas) {

            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
            // return mDatas.size();
        }


        //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = inflater.inflate(R.layout.item_scoring_rules, null, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.itemScoreName.setText(position + 1 + ". " + mDatas.get(position).getOptionName());


            //      holder.homeParent.setOnClickListener(this);
            //      holder.homeParent.setTag(position);

            holder.itemScoreUp.setTag(position);
            holder.itemScoreDown.setTag(position);

        }

        @Override
        public void onClick(final View v) {

            final int position = (int) v.getTag();

            switch (v.getId()) {
                case R.id.item_score_up:
                    if (position == 0) {
                        onMove(position, mDatas.size() - 1);
                    } else {
                        onMove(position, position - 1);
                    }
                    break;

                case R.id.item_score_down:
                    if (position == mDatas.size() - 1) {
                        onMove(position, 0);
                    } else {
                        onMove(position, position + 1);
                    }
                    break;
            }

            List<String> list = new MyArrayList();
            for (DataQuestionId.DataBean.InfoBean.OptionBean optionBean : mDatas) {
                list.add(String.valueOf(optionBean.getOptionId()));
            }

            answerBeans.get(qusetionId).setAnswer(list);

        }

        @Override
        public void onItemDelete(int positon) {
            mDatas.remove(positon);
            notifyItemRemoved(positon);
        }

        @Override
        public void onMove(int fromPosition, int toPosition) {
            Collections.swap(mDatas, fromPosition, toPosition);//交换数据
            notifyItemMoved(fromPosition, toPosition);
            notifyDataSetChanged();
        }

        public void setQusetionId(int qusetionId) {
            this.qusetionId = qusetionId;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_score_name)
            TextView itemScoreName;
            @Bind(R.id.home_parent)
            PercentRelativeLayout homeParent;
            @Bind(R.id.item_score_up)
            ImageButton itemScoreUp;
            @Bind(R.id.item_score_down)
            ImageButton itemScoreDown;

            public MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                itemScoreName.setTextSize(adjustFontSize(Tools.getWidth(mContext), 6));
                itemScoreUp.setOnClickListener(ScoreItemAdapter.this);
                itemScoreDown.setOnClickListener(ScoreItemAdapter.this);

            }

        }


    }


}
