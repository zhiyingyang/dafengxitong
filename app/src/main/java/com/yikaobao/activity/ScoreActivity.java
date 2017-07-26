package com.yikaobao.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yikaobao.R;
import com.yikaobao.tools.FavorEvent;
import com.yikaobao.tools.GitHubApi;
import com.yikaobao.tools.RetrofitClient;
import com.yikaobao.tools.Tools;
import com.yikaobao.adapter.DialogListAdapter;
import com.yikaobao.adapter.ScoreingAdapter;
import com.yikaobao.base.BaseActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.base.BaseData;
import com.yikaobao.data.DataScoreing;
import com.yikaobao.data.RequestParams;
import com.yikaobao.view.DialogRegister;
import com.yikaobao.view.TitleView;

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

public class ScoreActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.candidateslist_rv)
    RecyclerView candidateslistRv;
    ScoreingAdapter scoreingAdapter;
    @Bind(R.id.scoreing_bu)
    Button scoreingBu;
    @Bind(R.id.exam_title)
    TitleView examTitle;
    private int stationId;
    private String studentId;
    private int position;
    private String name;
    private DataScoreing.DataBean.StationInfoBean stationInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);
        stationId = getIntent().getIntExtra("stationId", -1);
        studentId = getIntent().getStringExtra("studentId");
        position = getIntent().getIntExtra("position", -1);
        name = getIntent().getStringExtra("name");

        initView();
        getData();
    }

    public void initView() {
        // RecyclerViewHeader recyclerViewHeader = (RecyclerViewHeader) findViewById(R.id.home_header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        candidateslistRv.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        candidateslistRv.setLayoutManager(layoutManager);
        scoreingAdapter = new ScoreingAdapter(this);
        //设置Adapter
        candidateslistRv.setAdapter(scoreingAdapter);
        scoreingBu.setOnClickListener(this);
        // recyclerViewHeader.attachTo(recyclerView);
        examTitle.setTitle(name);
    }

    public void getData() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "grade");
        requestParams.put("stationId", String.valueOf(stationId));
        requestParams.put("studentId", String.valueOf(studentId));

        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);
        //RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"Pack\":\"Login\",\"Interface\":\"login\",\"Username\":" + loginPhone.getText().toString() + ",\"Password\":" + loginPassword.getText().toString() + "}");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));

        Call<DataScoreing> call = repo.postScoreing(body);
        call.enqueue(new Callback<DataScoreing>() {
            @Override
            public void onResponse(Call<DataScoreing> call,
                                   Response<DataScoreing> response) {

                switch (response.body().getFlag()) {
                    case "Success":

                        if (response.body().getData().getCate() == null || response.body().getData().getCate().size() == 0) {
                            Tools.myMakeText(getApplicationContext(), "暂无考核表,请联系管理员。");
                            return;
                        }

                        scoreingAdapter.upData(response.body().getData().getCate(), response.body().getData().getPoints());
                        stationInfoBean = response.body().getData().getStationInfo();

                        break;
                    case "Error":
                        Tools.myMakeText(getApplicationContext(), response.body().getMsg());
                        break;

                    default:
                        Tools.myMakeText(getApplicationContext(), "未知错误");
                        break;

                }
            }

            @Override
            public void onFailure(Call<DataScoreing> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), "网络异常");
            }
        });

    }

    public void submitOverallScoreData() {

        List<DialogListAdapter.HospitalInfoBean> hospitalInfoBeen = new ArrayList<DialogListAdapter.HospitalInfoBean>();


        // 取xml文件格式的字符数组
        String[] good = getResources().getStringArray(R.array.score_item);
        int i = 1;
        for (String str : good) {
            DialogListAdapter.HospitalInfoBean hospitalInfoBean = new DialogListAdapter.HospitalInfoBean();
            hospitalInfoBean.setName(str);
            hospitalInfoBean.setId(i);
            hospitalInfoBeen.add(hospitalInfoBean);
            i++;
        }

        final RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "allSubmit");
        requestParams.put("stationId", String.valueOf(stationId));
        requestParams.put("studentId", String.valueOf(studentId));


        new DialogRegister(this, R.style.Dialog).addDatas(hospitalInfoBeen, "选择整体评价", new DialogRegister.getData() {
            @Override
            public void getData(String string, int id) {

                Retrofit retrofit = RetrofitClient.builderRetrofit();
                GitHubApi repo = retrofit.create(GitHubApi.class);
                requestParams.put("result", String.valueOf(id));

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
                                FavorEvent anyEventType = new FavorEvent();
                                anyEventType.setId(FavorEvent.Case_Sorceing);
                                anyEventType.setPosition(position);
                                EventBus.getDefault().postSticky(anyEventType);
                                ScoreActivity.this.finish();
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
        }).show();

    }


    public void submitData() {
        if (BaseApplication.user == null) return;

        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "gradeSubmit");
        requestParams.put("stationId", String.valueOf(stationId));
        requestParams.put("studentId", String.valueOf(studentId));


//        DataSubmitScoreing dataSubmitScoreing = new DataSubmitScoreing();
//        dataSubmitScoreing.setInterface("gradeSubmit");
//        dataSubmitScoreing.setPack("Kaoan");
//        dataSubmitScoreing.setStationId(stationId);
//        dataSubmitScoreing.setStudentId(Integer.parseInt(studentId));
//        dataSubmitScoreing.setUserId(BaseApplication.user.getData().getId());


        boolean flag = false;
        int munber = 0;
        for (int i = 0; i < scoreingAdapter.getDatas().size(); i++) {
            if (scoreingAdapter.getDatas().get(i).getResult() == Integer.MIN_VALUE) {
                flag = true;
                munber++;
            }
        }
        if (flag){
            Tools.myMakeText(getApplicationContext(), "还有" + munber + "题未完成打分,请完成后再提交");
            return;
        }

        requestParams.put("scoreInfo", scoreingAdapter.getDatas());

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
                        submitOverallScoreData();
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
    public void onClick(View v) {
        submitData();
    }


}
