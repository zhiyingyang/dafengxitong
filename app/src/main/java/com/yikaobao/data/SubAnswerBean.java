package com.yikaobao.data;

import com.yikaobao.view.MyArrayList;

import java.util.List;

/**
 * Created by pc on 2017/8/2.
 */

public class SubAnswerBean {
    /**
     * questionId : 1
     * type : 1
     * answer : 2
     */

    private int questionId;
    private int type;
    private String answer;
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
