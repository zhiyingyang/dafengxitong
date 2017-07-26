package com.yikaobao.tools;

//import com.jsmosce.activity.CandidatesAllListActivity;
//import com.jsmosce.base.BaseData;
//import com.jsmosce.data.DataAllCandiates;
//import com.jsmosce.data.DataCaseDesign;
//import com.jsmosce.data.DataCaseList;
//import com.jsmosce.data.DataHistoryCandidatesDetails;
//import com.jsmosce.data.DataHomeTest;
//import com.jsmosce.data.DataHospital;
//import com.jsmosce.data.DataScoreResult;
//import com.jsmosce.data.DataScoreing;
//import com.jsmosce.data.DataSelectDate;
//import com.jsmosce.data.DataSelectRoom;
//import com.jsmosce.data.DataStudentsList;
//import com.jsmosce.data.DataSubmitArticle;
//import com.jsmosce.data.DataSubmitScoreKind;
//import com.jsmosce.data.DataTestDesign;
//import com.jsmosce.data.DataUser;

import com.yikaobao.base.BaseData;
import com.yikaobao.data.DataCaseList;
import com.yikaobao.data.DataHomeTest;
import com.yikaobao.data.DataScoreing;
import com.yikaobao.data.DataStudentsList;
import com.yikaobao.data.DataUser;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lx on 2017/5/22.
 */
public interface GitHubApi {

    //    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataUser> postFlyRoute(@Body RequestBody route);//传入的参数为RequestBody
//
    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<DataHomeTest> postHomeTestRoute(@Body RequestBody route);//传入的参数为RequestBody


    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<ResponseBody> postStringFlyRoute(@Body RequestBody route);//传入的参数为RequestBody

    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<DataUser> postLogin(@Body RequestBody route);//传入的参数为RequestBody

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("public/")
    Call<BaseData> postBaseData(@Body RequestBody route);//传入的参数为RequestBody

    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<DataCaseList> postCaseList(@Body RequestBody route);//传入的参数为RequestBody

//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("Mobile")
//    Call<BaseData> postBaseData(@Body RequestBody route);//传入的参数为RequestBody
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataSelectDate> postSelectDate(@Body RequestBody route);//传入的参数为RequestBody
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataSelectRoom> postSelectRoom(@Body RequestBody route);//传入的参数为RequestBody
//
    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<DataStudentsList> postStudents(@Body RequestBody route);//考生列表

    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
    @POST("public/")
    Call<DataScoreing> postScoreing(@Body RequestBody route);//打分界面
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataCaseList> postCaseList(@Body RequestBody route);//传入的参数为RequestBody
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataSubmitScoreKind> postScoreKindList(@Body RequestBody route);//获取评分类别
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataScoreResult> postScoreResultsList(@Body RequestBody route);//获取评分标准
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataTestDesign> postTestDesign(@Body RequestBody route);//获取已有排程
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataCaseDesign> postCaseItem(@Body RequestBody route);//获取已有排程
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataSubmitArticle> postArticle(@Body RequestBody route);//获取已有物品
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataHospital> postHospital(@Body RequestBody route);//注册获取医院
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataHistoryCandidatesDetails> postHistoryCandidatesDetails(@Body RequestBody route);//注册历史记录考生考试结果
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<DataAllCandiates> postAllCandidates(@Body RequestBody route);//全部考生
//
//    @Headers({"Content-Type: application/json", "Accept: application/json", "Charset:utf-8"})
//    @POST("Mobile")
//    Call<CandidatesAllListActivity.StudentBaseData> postAddStudent(@Body RequestBody route);//提交考生


}
