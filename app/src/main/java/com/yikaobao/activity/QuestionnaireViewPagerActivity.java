package com.yikaobao.activity;

import android.os.Bundle;
import android.support.v4.view.BetterViewPager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.yikaobao.R;
import com.yikaobao.adapter.QuestionViewPagerAdapter;
import com.yikaobao.base.BaseActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.base.BaseData;
import com.yikaobao.data.AnswerBean;
import com.yikaobao.data.DataQuestionId;
import com.yikaobao.data.RequestParams;
import com.yikaobao.data.SubAnswerBean;
import com.yikaobao.tools.FavorEvent;
import com.yikaobao.tools.GitHubApi;
import com.yikaobao.tools.RetrofitClient;
import com.yikaobao.tools.Tools;
import com.yikaobao.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionnaireViewPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.question_viewpager)
    BetterViewPager questionViewpager;
    QuestionViewPagerAdapter questionViewPagerAdapter;
    @Bind(R.id.buttonFab)
    Button buttonFab;
    @Bind(R.id.home_title)
    TitleView homeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_view_pager);
        ButterKnife.bind(this);
        initView();
        getData();
        EventBus.getDefault().register(this);
    }

    public void initView() {
        questionViewpager.addOnPageChangeListener(this);
        buttonFab.setOnClickListener(this);
    }

    public void getData() {

        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Questionnaire");
        requestParams.put("Interface", "getQuestion");
        requestParams.put("caseId", BaseApplication.caseId);

        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));
        Call<DataQuestionId> call = repo.postQuestionnaire(body);

        call.enqueue(new Callback<DataQuestionId>() {
            @Override
            public void onResponse(Call<DataQuestionId> call,
                                   Response<DataQuestionId> response) {

                switch (response.body().getFlag()) {
                    case "Success":
                        //adapter.upData(response.body().getData().getInfo());

                        questionViewPagerAdapter = new QuestionViewPagerAdapter(getSupportFragmentManager(), response.body().getData().getInfo());

                        questionViewpager.setAdapter(questionViewPagerAdapter);

                        //初始化数据
                        if (response.body().getData().getInfo().get(0).getType() == 1) {
                            buttonFab.setVisibility(View.GONE);
                        }

                        homeTitle.setRightTv(1 + "/" + questionViewPagerAdapter.getCount());

                        break;
                    case "Error":
                        Tools.myMakeText(getApplicationContext(), response.body().getMsg().toString());
                        break;
                    default:
                        Tools.myMakeText(getApplicationContext(), "网络错误");
                        break;
                }
            }

            @Override
            public void onFailure(Call<DataQuestionId> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), t.getMessage());
            }
        });
    }

    public void submit() {

        if (BaseApplication.user == null) return;

        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Questionnaire");
        requestParams.put("Interface", "answerSubmit");
        requestParams.put("caseId", BaseApplication.caseId);
        requestParams.put("studentId", BaseApplication.user.getData().getId());

//        DataSubmitScoreing dataSubmitScoreing = new DataSubmitScoreing();
//        dataSubmitScoreing.setInterface("gradeSubmit");
//        dataSubmitScoreing.setPack("Kaoan");
//        dataSubmitScoreing.setStationId(stationId);
//        dataSubmitScoreing.setStudentId(Integer.parseInt(studentId));
//        dataSubmitScoreing.setUserId(BaseApplication.user.getData().getId());


        boolean flag = false;
        int munber = 0;

        for (AnswerBean answerBean : questionViewPagerAdapter.getDatas()) {
            if (answerBean.getAnswer().size() == 0 || !answerBean.isTrue()) {
                flag = true;
                munber++;
            }
        }

        if (flag) {
            Tools.myMakeText(getApplicationContext(), "还有" + munber + "题未完成,请完成后再提交");
            return;
        }

        //格式化一下答案
        List<SubAnswerBean> subAnswerBeans = new ArrayList<>();
        for (AnswerBean answerBean : questionViewPagerAdapter.getDatas()) {
            SubAnswerBean subAnswerBean = new SubAnswerBean();
            subAnswerBean.setQuestionId(answerBean.getQuestionId());
            subAnswerBean.setType(answerBean.getType());
            subAnswerBean.setAnswer(answerBean.getAnswer().toString());
            subAnswerBeans.add(subAnswerBean);
        }

        requestParams.put("answer", subAnswerBeans);


        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"
        ), Tools.toJson(requestParams));

        Call<BaseData> call = repo.postBaseData(body);

        call.enqueue(new Callback<BaseData>() {
            @Override
            public void onResponse(Call<BaseData> call,
                                   Response<BaseData> response) {

                BaseData baseData = response.body();

                switch (baseData.getFlag()) {
                    case "Success":
                        Tools.myMakeText(getApplicationContext(), "提交成功");
                        QuestionnaireViewPagerActivity.this.finish();

                        FavorEvent anyEventType = new FavorEvent();
                        anyEventType.setId(FavorEvent.RefreshMainActivity);
                        EventBus.getDefault().postSticky(anyEventType);

                        break;
                    case "Error":
                        Tools.myMakeText(getApplicationContext(), baseData.getMsg());
                        break;
                    default:
                        Tools.myMakeText(getApplicationContext(), "未知错误");
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseData> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), t.getMessage());
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        homeTitle.setRightTv(position + 1 + "/" + questionViewPagerAdapter.getCount());

        if (questionViewPagerAdapter.getDatas().get(position).getType() == 1 && !(position == questionViewPagerAdapter.getCount() - 1)) {
            buttonFab.setVisibility(View.GONE);
        } else {
            buttonFab.setVisibility(View.VISIBLE);
        }

        if (position == questionViewPagerAdapter.getCount() - 1) {
            buttonFab.setText("提交");
        } else {
            buttonFab.setText("完成");
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        Button buttonFab = (Button) view;

        Tools.closeSoftKeybord(getWindow().getDecorView(), getApplicationContext());

        if (buttonFab.getText().equals("完成")) {
            questionViewPagerAdapter.getDatas().get(questionViewpager.getCurrentItem()).setTrue(true);
            questionViewpager.setCurrentItem(questionViewpager.getCurrentItem() + 1, true);
            return;
        }

        if (questionViewpager.getCurrentItem() == questionViewPagerAdapter.getCount() - 1) {
            questionViewPagerAdapter.getDatas().get(questionViewpager.getCurrentItem()).setTrue(true);
        }

        submit();
    }

    @Subscribe
    public void onEvent(FavorEvent event) {
        //选择关联
        if (event.getId() == FavorEvent.ViewPagerNext) {
            questionViewPagerAdapter.getDatas().get(questionViewpager.getCurrentItem()).setTrue(true);
            questionViewpager.setCurrentItem(questionViewpager.getCurrentItem() + 1, true);
        }
    }


}
