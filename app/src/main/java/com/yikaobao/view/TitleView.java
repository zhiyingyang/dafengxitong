package com.yikaobao.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikaobao.R;

/**
 * Created by Administrator on 2016/7/29. //自定义标题栏
 */
public class TitleView extends LinearLayout implements View.OnClickListener {
    //标题
    private TextView title;
    //返回按钮
    private LinearLayout back;
    //右边文字
    //右边按钮点击事件
    private RightOnClick rightOnClick;
    private View rightImg;
    private TextView rightTv;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public interface RightOnClick {
        void onClick(View view);
    }

    //取消返回键的监听事件
    public void setBackNull() {
        back.setOnClickListener(null);
    }

    //設置右邊的點擊事件
    public void setRightOnClick(RightOnClick rightOnClick) {
        this.rightOnClick = rightOnClick;
    }

    /**
     * 初始化View
     */
    public void initView(Context context, AttributeSet attrs) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.view_title, this, true);
        title = (TextView) v.findViewById(R.id.view_title_title);
        back = (LinearLayout) v.findViewById(R.id.view_title_back);
        rightImg = findViewById(R.id.view_title_right_view);
        rightTv = (TextView) findViewById(R.id.view_title_right_tv);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.view_title_right_LinearLayout);
        linearLayout.setOnClickListener(this);
        back.setOnClickListener(this);

        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleView);
        String string = typedArray.getString(R.styleable.titleView_titleText);
        title.setText(string != null ? string : "标题");

        int id = typedArray.getResourceId(R.styleable.titleView_rightImg, 0);
        if (id != 0) {
            rightImg.setBackgroundResource(id);
            rightImg.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String titleString) {
        title.setText(titleString);
    }

    public void setRightImg(int id) {
        rightImg.setBackgroundResource(id);
        rightImg.setVisibility(VISIBLE);
    }

    public void setRightTv(String string) {
        rightTv.setText(string);
        rightTv.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.view_title_back) {
            ((Activity) v.getContext()).finish();
        } else {
            if (rightOnClick != null) {
                rightOnClick.onClick(v);
            }
        }
    }
}
