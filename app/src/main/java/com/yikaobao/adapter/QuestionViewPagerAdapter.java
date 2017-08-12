package com.yikaobao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.yikaobao.Fragment.ViewpagerFragment;
import com.yikaobao.data.AnswerBean;
import com.yikaobao.data.DataQuestionId;
import com.yikaobao.view.MyArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pc on 2017/8/9.
 * 调查报告viewpager
 */

public class QuestionViewPagerAdapter extends FragmentStatePagerAdapter{


    private List<DataQuestionId.DataBean.InfoBean> mDatas = new ArrayList<DataQuestionId.DataBean.InfoBean>();

    private List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();
    List<ViewpagerFragment> fragments = new ArrayList<ViewpagerFragment>();

    public QuestionViewPagerAdapter(FragmentManager fm, List<DataQuestionId.DataBean.InfoBean> mDatas) {
        super(fm);
        this.mDatas = mDatas;
        //初始化 保存答案的数组
        for (int i = 0; i < mDatas.size(); i++) {
            AnswerBean answerBean = new AnswerBean();
            answerBean.setQuestionId(mDatas.get(i).getQuestionId());
            answerBean.setType(mDatas.get(i).getType());

            if (mDatas.get(i).getType() == 4) {
                List<String> list = new MyArrayList();
                for (DataQuestionId.DataBean.InfoBean.OptionBean optionBean : mDatas.get(i).getOption()) {
                    list.add(String.valueOf(optionBean.getOptionId()));
                }
                answerBean.setAnswer(list);
            }

            answerBeans.add(answerBean);
        }
    }

    @Override
    public ViewpagerFragment getItem(int position) {

        ViewpagerFragment viewpagerFragment;
        if (fragments.size() == position) {

            viewpagerFragment = new ViewpagerFragment();
            viewpagerFragment.setData(mDatas.get(position), position, answerBeans);
            fragments.add(viewpagerFragment);

        } else {

            viewpagerFragment = fragments.get(position);
            viewpagerFragment.setData(mDatas.get(position), position, answerBeans);

        }

        return viewpagerFragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    public List<AnswerBean> getDatas() {
        return answerBeans;
    }

}
