package com.yikaobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.yikaobao.MainActivity;
import com.yikaobao.R;
import com.yikaobao.tools.AppManager;
import com.yikaobao.tools.FavorEvent;
import com.yikaobao.tools.GitHubApi;
import com.yikaobao.tools.RetrofitClient;
import com.yikaobao.tools.Tools;
import com.yikaobao.adapter.CandidatesListAdapter;
import com.yikaobao.base.BaseActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.data.DataStudentsList;
import com.yikaobao.data.RequestParams;
import com.yikaobao.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//考生列表
public class CandidatesListActivity extends BaseActivity {
    CandidatesListAdapter candidatesListAdapter;

    @Bind(R.id.candidateslist_header)
    RecyclerViewHeader candidateslistHeader;
    @Bind(R.id.exam_kaoanbianhao)
    TextView examKaoanbianhao;
    @Bind(R.id.exam_kaohedanwei)
    TextView examKaohedanwei;
    @Bind(R.id.exam_ceyanriqi)
    TextView examCeyanriqi;
    @Bind(R.id.exam_ceyanhexing)
    TextView examCeyanhexing;
    @Bind(R.id.exam_kaoanmingchen)
    TextView examKaoanmingchen;
    @Bind(R.id.exam_kaoguan)
    TextView examKaoguan;
    @Bind(R.id.exam_keshi)
    TextView examKeshi;
    @Bind(R.id.text_phone)
    TextView textPhone;
    @Bind(R.id.exam_title)
    TitleView examTitle;
    private int stationId;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_list);
        ButterKnife.bind(this);
        initView();
        stationId = getIntent().getIntExtra("stationId", 0);
        state = getIntent().getIntExtra("state", 0);
        getData();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(FavorEvent event) {
        //选择关联
        if (event.getId() == FavorEvent.Case_Sorceing) {
            candidatesListAdapter.remove(event.getPosition());
            examKeshi.setText("人        数：" + candidatesListAdapter.getItemCount());

            if (candidatesListAdapter.getItemCount() == 0) {
                AppManager.getAppManager().finishAllActivity();
                Intent LaunchIntent = new Intent();
                LaunchIntent.setClass(getApplicationContext(), MainActivity.class);
                LaunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(LaunchIntent);
            }
        }
    }

    public void initView() {
        RecyclerViewHeader recyclerViewHeader = (RecyclerViewHeader) findViewById(R.id.candidateslist_header);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.candidateslist_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        candidatesListAdapter = new CandidatesListAdapter(getApplicationContext());

        //设置Adapter
        recyclerView.setAdapter(candidatesListAdapter);
        recyclerViewHeader.attachTo(recyclerView);
        examTitle.setTitle(getIntent().getStringExtra("title"));
    }

    public void getData() {

        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "detail");
        requestParams.put("stationId", String.valueOf(stationId));

        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);
        RequestBody body;
        if (state == 0) {
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));
        } else {
            //打完分的
            textPhone.setText("总分");
            requestParams.put("Interface", "doneInfo");
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));
        }

        Call<DataStudentsList> call = repo.postStudents(body);

        call.enqueue(new Callback<DataStudentsList>() {
            @Override
            public void onResponse(Call<DataStudentsList> call,
                                   Response<DataStudentsList> response) {

                DataStudentsList dataHomeTest = response.body();
                if(dataHomeTest==null)return;

                switch (dataHomeTest.getFlag()) {

                    case "Success":
                        //添加数据
                        candidatesListAdapter.upData(dataHomeTest.getData().getUserInfo(), dataHomeTest.getData().getStationInfo());
                        examKaoanbianhao.setText(examKaoanbianhao.getText() + dataHomeTest.getData().getStationInfo().getText_case_number());
                        examCeyanhexing.setText(examCeyanhexing.getText() + dataHomeTest.getData().getStationInfo().getText_core());
                        examKaohedanwei.setText(examKaohedanwei.getText() + dataHomeTest.getData().getStationInfo().getWork_unit());
                        examCeyanriqi.setText(examCeyanriqi.getText() + dataHomeTest.getData().getStationInfo().getTextTime());
                        examKaoguan.setText(examKaoguan.getText() + BaseApplication.user.getData().getUsername());
                        examKaoanmingchen.setText(examKaoanmingchen.getText() + dataHomeTest.getData().getStationInfo().getText_case_name());
                        examKeshi.setText(examKeshi.getText().toString() + dataHomeTest.getData().getUserInfo().size());

                        break;
                    case "Error":
                        Tools.myMakeText(getApplicationContext(), dataHomeTest.getMsg());
                        break;

                    default:
                        Tools.myMakeText(getApplicationContext(), "未知错误");
                        break;

                }
            }

            @Override
            public void onFailure(Call<DataStudentsList> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), t.getMessage());
            }
        });

    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
