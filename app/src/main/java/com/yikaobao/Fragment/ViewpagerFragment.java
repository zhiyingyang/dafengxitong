package com.yikaobao.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.adapter.QuestionAdapter;
import com.yikaobao.adapter.RecycleItemTouchHelper;
import com.yikaobao.data.AnswerBean;
import com.yikaobao.data.DataQuestionId;
import com.yikaobao.tools.FavorEvent;
import com.yikaobao.tools.Tools;
import com.yikaobao.view.MyArrayList;
import com.yikaobao.view.MyRadioGroup;
import com.yikaobao.view.mradio.CheckBox;
import com.yikaobao.view.mradio.RadioButton;
import com.yikaobao.view.percentsuppor.PercentLinearLayout;
import com.yikaobao.view.percentsuppor.PercentRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.StringUtils;

import static com.yikaobao.adapter.QuestionAdapter.adjustFontSize;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ViewpagerFragment extends Fragment implements View.OnTouchListener {

    DataQuestionId.DataBean.InfoBean linkedList;
    int position;
    View rootView;
    @Bind(R.id.viewpager_parent)
    FrameLayout viewpagerParent;
    private LayoutInflater inflater;
    private List<AnswerBean> answerBeans;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setOnTouchListener(this);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    public void setData(DataQuestionId.DataBean.InfoBean linkedList, int position, List<AnswerBean> answerBeans) {
        this.position = position;
        this.linkedList = linkedList;
        this.answerBeans = answerBeans;
    }

    public void initView() {

        inflater = LayoutInflater.from(getActivity().getApplicationContext());
        View view = null;
        switch (linkedList.getType()) {
            case 1:

                view = inflater.inflate(R.layout.item_question_danxuan, null, false);

                TextView title = (TextView) view.findViewById(R.id.item_qusetion_item);
                title.setText(linkedList.getTitle());
                TextView type = (TextView) view.findViewById(R.id.item_qusetion_type);
                type.setText("单选题");
                type.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 5));


                final MyRadioGroup danxuan = (MyRadioGroup) view.findViewById(R.id.qusetion_option);
                danxuan.removeAllViews();
                danxuan.setTag(position);
                final int[] flag = new int[1];

                danxuan.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                        int position = (int) group.getTag();
//                        if (flag[0]%2>0) {
//                            flag[0]++;
//                            return;
//                        }
//                        flag[0]++;
                        // flag[0] = false;
                        answerBeans.get(position).setTrue(true);
                        answerBeans.get(position).getAnswer().clear();
                        answerBeans.get(position).getAnswer().add(String.valueOf(linkedList.getOption().get(checkedId).getOptionId()));
//
//                        FavorEvent anyEventType = new FavorEvent();
//                        anyEventType.setId(FavorEvent.ViewPagerNext);
//                        EventBus.getDefault().postSticky(anyEventType);


                    }
                });

                title.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6.5));

                for (int i = 0; i < linkedList.getOption().size(); i++) {
                    RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.my_radio, null);
                    radioButton.setId(i);
                    radioButton.setText(linkedList.getOption().get(i).getOptionName());
                    danxuan.addView(radioButton);


                    radioButton.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6));
                    radioButton.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.colorBlack));

                    for (String answer : answerBeans.get(position).getAnswer()) {
                        if (linkedList.getOption().get(i).getOptionId() == Integer.parseInt(answer)) {
                            radioButton.setChecked(true);
                        }
                    }
                }

                break;

            case 2:

                view = inflater.inflate(R.layout.item_question_duoxuan, null, false);


                TextView title2 = (TextView) view.findViewById(R.id.item_qusetion_item);
                title2.setText(linkedList.getTitle());
                title2.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6.5));
                TextView type2 = (TextView) view.findViewById(R.id.item_qusetion_type);
                type2.setText("多选题");
                type2.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 5));

                PercentLinearLayout duoxuan = (PercentLinearLayout) view.findViewById(R.id.qusetion_option);
                duoxuan.removeAllViews();
                duoxuan.setTag(position);

                for (int i = 0; i < linkedList.getOption().size(); i++) {
                    CheckBox checkBox = (CheckBox) inflater.inflate(R.layout.my_checkbox, null);
                    checkBox.setId(i);
                    checkBox.setText(linkedList.getOption().get(i).getOptionName());
                    duoxuan.addView(checkBox);
                    checkBox.setTag(linkedList.getOption().get(i).getOptionId());
                    checkBox.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6));
                    checkBox.setTextColor(getActivity().getApplicationContext().getResources().getColor(R.color.colorBlack));

                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            int position = (int) ((ViewGroup) compoundButton.getParent()).getTag();
                            if (b) {
                                answerBeans.get(position).getAnswer().add(String.valueOf(compoundButton.getTag()));
                            } else {
                                answerBeans.get(position).getAnswer().remove(String.valueOf(compoundButton.getTag()));
                            }
                        }
                    });

                    for (String answer : answerBeans.get(position).getAnswer()) {
                        if (linkedList.getOption().get(i).getOptionId() == Integer.parseInt(answer)) {
                            checkBox.setChecked(true);
                        }
                    }
                }

                break;

            case 3:

                view = inflater.inflate(R.layout.item_question_txt, null, false);

                TextView title3 = (TextView) view.findViewById(R.id.item_qusetion_item);
                title3.setText(linkedList.getTitle());
                title3.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6.5));
                TextView type3 = (TextView) view.findViewById(R.id.item_qusetion_type);
                type3.setText("问答题");

                type3.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 5));

                EditText editText = (EditText) view.findViewById(R.id.item_qusetion_et);

                editText.setTag(position);
                editText.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6));
                //  if (StringUtils.isEmpty(answerBeans.get(position).getAnswer().toString()))
                if (answerBeans.get(position).getAnswer().size() > 0) {
                    editText.setText(answerBeans.get(position).getAnswer().get(0));
                }
                editText.addTextChangedListener(new TextWatcher() {

                    public TextWatcher setMyeditText(EditText myeditText) {
                        this.myeditText = myeditText;
                        return this;
                    }

                    private EditText myeditText;

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        int position = (int) myeditText.getTag();
                        answerBeans.get(position).getAnswer().clear();
                        answerBeans.get(position).getAnswer().add(myeditText.getText().toString());
                    }
                }.setMyeditText(editText));


                break;
            case 4:

                view = inflater.inflate(R.layout.item_question_paixu, null, false);

                TextView title4 = (TextView) view.findViewById(R.id.item_qusetion_item);
                title4.setText(linkedList.getTitle());
                title4.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 6.5));
                TextView type4 = (TextView) view.findViewById(R.id.item_qusetion_type);
                type4.setText("排序题");
                type4.setTextSize(adjustFontSize(Tools.getWidth(getActivity().getApplicationContext()), 5));

                ViewpagerFragment.ScoreItemAdapter scoreItemAdapter = new ViewpagerFragment.ScoreItemAdapter(getContext().getApplicationContext());

                scoreItemAdapter.upData(linkedList.getOption());
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.item_qusetion_px_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
                //设置布局管理器
                recyclerView.setHasFixedSize(true);
                //设置为垂直布局，这也是默认的
                layoutManager.setOrientation(OrientationHelper.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(scoreItemAdapter);
                scoreItemAdapter.setQusetionId(position);

                break;

        }

        viewpagerParent.addView(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * Created by lx on 2017/5/18.
     */
    //排序
    public class ScoreItemAdapter extends RecyclerView.Adapter<ViewpagerFragment.ScoreItemAdapter.MyViewHolder> implements View.OnClickListener, RecycleItemTouchHelper.ItemTouchHelperCallback {

        /**
         * Created by Administrator on 2016/8/3.
         */

        private List<DataQuestionId.DataBean.InfoBean.OptionBean> mDatas = new LinkedList<DataQuestionId.DataBean.InfoBean.OptionBean>();

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
            this.mDatas = datas;
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }


        /**
         * 刷新数据
         */
        public void upData(List<DataQuestionId.DataBean.InfoBean.OptionBean> datas) {
            this.mDatas = datas;
            // mDatas.clear();
            // mDatas.addAll(datas);
            notifyDataSetChanged();

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
            // return mDatas.size();
        }


        //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
        @Override
        public ViewpagerFragment.ScoreItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = inflater.inflate(R.layout.item_scoring_rules, null, false);
            ViewpagerFragment.ScoreItemAdapter.MyViewHolder holder = new ViewpagerFragment.ScoreItemAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewpagerFragment.ScoreItemAdapter.MyViewHolder holder, int position) {

            holder.itemScoreName.setText(position + 1 + ". " + mDatas.get(position).getOptionName());
            //      holder.homeParent.setTag(position);
            holder.itemScoreUp.setTag(position);
            holder.itemScoreDown.setTag(position);
            holder.homeParent.setTag(position);
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
                case R.id.home_parent:
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
                itemScoreUp.setOnClickListener(ViewpagerFragment.ScoreItemAdapter.this);
                homeParent.setOnClickListener(ViewpagerFragment.ScoreItemAdapter.this);
                //itemScoreDown.setOnClickListener(ViewpagerFragment.ScoreItemAdapter.this);

            }
        }

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Tools.closeSoftKeybord(view, getActivity().getApplicationContext());
        return false;
    }


}
