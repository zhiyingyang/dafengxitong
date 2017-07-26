package com.yikaobao.data;

import java.util.List;

/**
 * Created by lx on 2017/7/13.
 */

public class DataSubmitScoreing {


    /**
     * Pack : Kaoan
     * Interface : gradeSubmit
     * stationId : 3
     * userId : 2
     * studentId : 4
     * scoreInfo : [{"case_assessmentId":5,"result":9},{"case_assessmentId":6,"result":8},{"case_assessmentId":7,"result":6}]
     */

    private String Pack;
    private String Interface;
    private int stationId;
    private int userId;
    private int studentId;
    private List<ScoreInfoBean> scoreInfo;

    public String getPack() {
        return Pack;
    }

    public void setPack(String Pack) {
        this.Pack = Pack;
    }

    public String getInterface() {
        return Interface;
    }

    public void setInterface(String Interface) {
        this.Interface = Interface;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<ScoreInfoBean> getScoreInfo() {
        return scoreInfo;
    }

    public void setScoreInfo(List<ScoreInfoBean> scoreInfo) {
        this.scoreInfo = scoreInfo;
    }

    public static class ScoreInfoBean {

        /**
         * case_assessmentId : 5
         * result : 9
         */

        private int case_assessmentId;
        private int result = Integer.MIN_VALUE;

        public int getCase_assessmentId() {
            return case_assessmentId;
        }

        public ScoreInfoBean setCase_assessmentId(int case_assessmentId) {
            this.case_assessmentId = case_assessmentId;
            return this;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
