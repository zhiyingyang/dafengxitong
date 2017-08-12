package com.yikaobao.tools;

//通知类
public class FavorEvent<T> {
    //
    public final static int  ViewPagerNext= 01;
    //修改评分项目
    public final static int Case_Detail = 02;
    //添加评分项目
    public final static int Case_add = 03;
    //打完分后
    public final static int Case_Sorceing = 04;
    //退出登录
    public final static int Exit_App = 05;
    //刷新首页
    public final static int RefreshMainActivity = 06;
    //被挤出
    public final static int SqueezedOut = 07;

    private int id;
    //列表的position
    private int position;
    //列表的Object
    private Object object;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}