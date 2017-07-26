package com.yikaobao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.yikaobao.activity.LoginActivity;
import com.yikaobao.adapter.HomeAdapter;
import com.yikaobao.base.BaseActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.data.DataHomeTest;
import com.yikaobao.data.RequestParams;
import com.yikaobao.tools.FavorEvent;
import com.yikaobao.tools.GitHubApi;
import com.yikaobao.tools.RetrofitClient;
import com.yikaobao.tools.SharedPMananger;
import com.yikaobao.tools.Tools;
import com.yikaobao.view.TitleView;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    int page;
    @Bind(R.id.home_title)
    TitleView homeTitle;
    @Bind(R.id.buttonFab)
    Button buttonFab;
    @Bind(R.id.show_evaluation_eva)
    RecyclerView showEvaluationEva;
    @Bind(R.id.home_refresh)
    MaterialRefreshLayout homeRefresh;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // findViewById(R.id.home_close).setOnClickListener(this);

//        for (int i = 0; i < 10; i++) {
//            dataTestCases.add(new DataUser());
//        }
        //  drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        TitleView titleView = (TitleView) findViewById(R.id.home_title);
        // titleView.setBackNull();
        // drawer.addDrawerListener(this);
        initView();
        getData();
        //  homeRefresh.autoRefresh();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //
    }

    public void initView() {
        // RecyclerViewHeader recyclerViewHeader = (RecyclerViewHeader) findViewById(R.id.home_header);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        showEvaluationEva.setHasFixedSize(true);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        showEvaluationEva.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(getApplicationContext());
        //设置Adapter
        showEvaluationEva.setAdapter(homeAdapter);
        // recyclerViewHeader.attachTo(recyclerView);

        homeRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {

            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                page = 0;
                getData();
            }

            @Override
            public void onfinish() {
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                page++;
                getData();
            }

        });


        findViewById(R.id.buttonFab).setOnClickListener(this);
        // homeTitle.setRightImg(R.drawable.icon_user);
        if (BaseApplication.user != null) {
            homeTitle.setTitle(BaseApplication.user.getData().getUsername());
        }
    }


    public void getData() {
        if (BaseApplication.user == null) return;
        RequestParams requestParams = new RequestParams();
        requestParams.put("Pack", "Kaoan");
        requestParams.put("Interface", "caseList");

        Retrofit retrofit = RetrofitClient.builderRetrofit();
        GitHubApi repo = retrofit.create(GitHubApi.class);                         // {"Pack":"Kaoan","Interface":"caseList","userId":2}
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Tools.toJson(requestParams));
        Call<DataHomeTest> call = repo.postHomeTestRoute(body);

        call.enqueue(new Callback<DataHomeTest>() {
            @Override
            public void onResponse(Call<DataHomeTest> call,
                                   Response<DataHomeTest> response) {
                DataHomeTest dataHomeTest = response.body();

                switch (dataHomeTest.getFlag()) {
                    case "Success":
                        if (page == 0) {
                            homeAdapter.upData(dataHomeTest.getData());
                        } else {
                            homeAdapter.addData(dataHomeTest.getData());
                        }

                        break;
                    case "Error":
                        if (page > 0) {
                            page--;
                        }
                        Tools.myMakeText(getApplicationContext(), dataHomeTest.getMsg());

                        break;
                    default:
                        if (page > 0) {
                            page--;
                        }
                        Tools.myMakeText(getApplicationContext(), "网络错误");
                        break;

                }

                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        homeRefresh.finishRefresh();
                        homeRefresh.finishRefreshLoadMore();
                    }
                }.sendEmptyMessageDelayed(0, 500);


            }

            @Override
            public void onFailure(Call<DataHomeTest> call, Throwable t) {
                Tools.myMakeText(getApplicationContext(), t.getMessage());
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        homeRefresh.finishRefresh();
                        homeRefresh.finishRefreshLoadMore();
                    }
                }.sendEmptyMessageDelayed(0, 500);
            }
        });

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_LONG).show();
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//
//        } else if (id == R.id.nav_manage) {
//
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.END);
//        return true;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_title_right_LinearLayout:

                Intent intent2;
                if (BaseApplication.user == null) {
                    intent2 = new Intent(this, LoginActivity.class);
                    startActivity(intent2);
                    return;
                }

//                intent2 = new Intent(this, MeActivity.class);
//                startActivity(intent2);

                break;
            case R.id.buttonFab:
                SharedPMananger.remove(SharedPMananger.USER);
                BaseApplication.user = null;

                Intent i = new Intent();
                i.setClass(MainActivity.this, LoginActivity.class);
                startActivity(i);

                this.finish();
                break;

        }

    }


    @Subscribe
    public void onEvent(FavorEvent event) {
        //选择关联
        if (event.getId() == FavorEvent.Exit_App) {
            this.finish();
        }
        if (event.getId() == FavorEvent.RefreshMainActivity) {
            homeRefresh.autoRefresh();
        }
    }

}
