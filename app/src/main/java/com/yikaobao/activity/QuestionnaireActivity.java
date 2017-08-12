package com.yikaobao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yikaobao.R;
import com.yikaobao.adapter.QuestionAdapter;
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
import com.yikaobao.view.MyArrayList;
import com.yikaobao.view.RecycleViewDivider;

import org.greenrobot.eventbus.EventBus;

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

//调查问卷
public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.qa_rv)
    RecyclerView qaRv;
    QuestionAdapter adapter;
    @Bind(R.id.buttonFab)
    Button buttonFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    public void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        qaRv.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        qaRv.setLayoutManager(layoutManager);

        adapter = new QuestionAdapter(getApplicationContext());
        //设置Adapter
        qaRv.setAdapter(adapter);

        buttonFab.setOnClickListener(this);

    }

//        mReactRootView = new ReactRootView(this);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModuleName("index.android") //对应index.android.js
//                .addPackage(new MainReactPackage())
//                //.setUseDeveloperSupport(BuildConfig.DEBUG) //开发者支持，BuildConfig.DEBUG的值默认是false，无法使用开发者菜单
//                .setUseDeveloperSupport(true) //开发者支持,开发的时候要设置为true，不然无法使用开发者菜单
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();
//        //这里的ReactNativeView对应index.android.js中AppRegistry.registerComponent('ReactNativeView', () => ReactNativeView)的ReactNativeView
//        mReactRootView.startReactApplication(mReactInstanceManager, "HelloWorld", null);
//        setContentView(mReactRootView);


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
                        adapter.upData(response.body().getData().getInfo());
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonFab:

                submit();
                break;
        }
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

        for (AnswerBean answerBean : adapter.getDatas()) {
            if (answerBean.getAnswer().size() == 0) {
                flag = true;
                munber++;
            }
        }

        if (flag) {
            Tools.myMakeText(getApplicationContext(), "还有" + munber + "题完成,请完成后再提交");
            return;
        }

        //格式化一下答案
        List<SubAnswerBean> subAnswerBeans = new ArrayList<>();
        for (AnswerBean answerBean : adapter.getDatas()) {
            SubAnswerBean subAnswerBean = new SubAnswerBean();
            subAnswerBean.setQuestionId(answerBean.getQuestionId());
            subAnswerBean.setType(answerBean.getType());
            subAnswerBean.setAnswer(answerBean.getAnswer().toString());
            subAnswerBeans.add(subAnswerBean);
        }

        requestParams.put("answer", subAnswerBeans);

        //    dataSubmitScoreing.setScoreInfo(scoreingAdapter.getDatas());

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
                        QuestionnaireActivity.this.finish();

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
}



