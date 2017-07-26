package com.yikaobao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yikaobao.R;

import butterknife.Bind;
import butterknife.ButterKnife;
//调查问卷
public class QuestionnaireActivity extends AppCompatActivity {

    @Bind(R.id.qa_rv)
    RecyclerView qaRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        ButterKnife.bind(this);
    }
}
