package com.yikaobao.data;

import java.util.List;

/**
 * Created by lx on 2017/6/12.
 */
//内部考站
public class DataCaseList {


    /**
     * flag : Success
     * msg : null
     * data : [{"Id":1,"name":"考站一","addTime":"0000-00-00 00:00:00","examier":1,"examinee":"4,5,6,7","teach":2,"isSp":1,"sper":2,"caseId":1,"location":"考站一的位置","text_core":"核心一","state":0},{"Id":2,"name":"考站二","addTime":"0000-00-00 00:00:00","examier":2,"examinee":"7","teach":8,"isSp":1,"sper":8,"caseId":1,"location":"考站二的位置","text_core":"核心二","state":1},{"Id":3,"name":"考站三","addTime":"0000-00-00 00:00:00","examier":2,"examinee":"4,7","teach":2,"isSp":0,"sper":2,"caseId":1,"location":"考站三的位置","text_core":"核心三","state":0}]
     */

    private String flag;
    private String msg;
    private List<DataBean> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 1
         * name : 考站一
         * addTime : 0000-00-00 00:00:00
         * examier : 1
         * examinee : 4,5,6,7
         * teach : 2
         * isSp : 1
         * sper : 2
         * caseId : 1
         * location : 考站一的位置
         * text_core : 核心一
         * state : 0 未打完 1打完了
         */

        private int Id;
        private String name;
        private String addTime;
        private int examier;
        private String examinee;
        private int teach;
        private int isSp;
        private int sper;
        private int caseId;
        private String location;
        private String text_core;
        private int state;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getExamier() {
            return examier;
        }

        public void setExamier(int examier) {
            this.examier = examier;
        }

        public String getExaminee() {
            return examinee;
        }

        public void setExaminee(String examinee) {
            this.examinee = examinee;
        }

        public int getTeach() {
            return teach;
        }

        public void setTeach(int teach) {
            this.teach = teach;
        }

        public int getIsSp() {
            return isSp;
        }

        public void setIsSp(int isSp) {
            this.isSp = isSp;
        }

        public int getSper() {
            return sper;
        }

        public void setSper(int sper) {
            this.sper = sper;
        }

        public int getCaseId() {
            return caseId;
        }

        public void setCaseId(int caseId) {
            this.caseId = caseId;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getText_core() {
            return text_core;
        }

        public void setText_core(String text_core) {
            this.text_core = text_core;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
