package com.yikaobao.data;

import com.yikaobao.view.MyArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/8/2.
 */

public class AnswerBean {
    /**
     * questionId : 1
     * type : 1
     * answer : 2
     */

    private int questionId;
    private int type;
    private boolean isTrue;

    private List<String> answer = new MyArrayList();

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

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
