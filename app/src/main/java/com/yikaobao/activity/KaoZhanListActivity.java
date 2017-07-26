package com.yikaobao.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.yikaobao.R;
import com.yikaobao.tools.GitHubApi;
import com.yikaobao.tools.RetrofitClient;
import com.yikaobao.tools.Tools;
import com.yikaobao.adapter.CaseListAdapter;
import com.yikaobao.base.BaseActivity;
import com.yikaobao.data.DataCaseList;
import com.yikaobao.data.RequestParams;
import com.yikaobao.view.TitleView;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//已有考案
public class KaoZhanListActivity extends BaseActivity {

    @Bind(R.id.case_list_title)
    TitleView caseListTitle;
    @Bind(R.id.case_list_rv)
    RecyclerView caseListRv;
    //    @Bind(R.id.fab)
//    FloatingActionMenu fab;
    CaseListAdapter caseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_list);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        caseListRv.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        caseListRv.setLayoutManager(layoutManager);
        caseListAdapter = new CaseListAdapter(getApplicationContext());
        //设置Adapter
        caseListRv.setAdapter(caseListAdapter);
        caseListTitle.setTitle(getIntent().getStringExtra("title"));
    }


    public void getData() {

        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "index");

        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));
        Call<DataCaseList> call = repo.postCaseList(body);

        call.enqueue(new Callback<DataCaseList>() {
            @Override
            public void onResponse(Call<DataCaseList> call,
                                   Response<DataCaseList> response) {

                switch (response.body().getFlag()) {
                    case "Success":
                        caseListAdapter.upData(response.body().getData());
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
            public void onFailure(Call<DataCaseList> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), t.getMessage());
            }
        });

    }

}
