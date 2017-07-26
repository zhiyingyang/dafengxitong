package com.yikaobao.data;

import java.util.List;

/**
 * Created by lx on 2017/7/12.
 */

public class DataScoreing {

    /**
     * flag : Success
     * msg : null
     * data : {"cate":[{"Id":5,"describe":"考站san评分类别描述3","name":"考站san评分类别2"},{"Id":6,"describe":"考站san评分类别描述2","name":"考站san评分类别1"},{"Id":7,"describe":"考站san评分类别描述1","name":"考站san评分类别3"}],"points":[{"Id":12,"describe":"优秀","min":0,"max":7},{"Id":13,"describe":"良好","min":0,"max":7},{"Id":14,"describe":"中等","min":0,"max":7},{"Id":15,"describe":"差","min":0,"max":7}],"stationInfo":{"Id":3,"name":"考站三","addTime":"0000-00-00 00:00:00","examier":2,"min":0,"max":7,"examinee":"4,7","teach":2,"isSp":0,"sper":2,"caseId":1,"location":"考站三的位置","text_core":"核心三","state":0}}
     */

    private String flag;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cate : [{"Id":5,"describe":"考站san评分类别描述3","name":"考站san评分类别2"},{"Id":6,"describe":"考站san评分类别描述2","name":"考站san评分类别1"},{"Id":7,"describe":"考站san评分类别描述1","name":"考站san评分类别3"}]
         * points : [{"Id":12,"describe":"优秀","min":0,"max":7},{"Id":13,"describe":"良好","min":0,"max":7},{"Id":14,"describe":"中等","min":0,"max":7},{"Id":15,"describe":"差","min":0,"max":7}]
         * stationInfo : {"Id":3,"name":"考站三","addTime":"0000-00-00 00:00:00","examier":2,"min":0,"max":7,"examinee":"4,7","teach":2,"isSp":0,"sper":2,"caseId":1,"location":"考站三的位置","text_core":"核心三","state":0}
         */

        private StationInfoBean stationInfo;
        private List<CateBean> cate;
        private List<PointsBean> points;

        public StationInfoBean getStationInfo() {
            return stationInfo;
        }

        public void setStationInfo(StationInfoBean stationInfo) {
            this.stationInfo = stationInfo;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<PointsBean> getPoints() {
            return points;
        }

        public void setPoints(List<PointsBean> points) {
            this.points = points;
        }

        public static class StationInfoBean {
            /**
             * Id : 3
             * name : 考站三
             * addTime : 0000-00-00 00:00:00
             * examier : 2
             * min : 0
             * max : 7
             * examinee : 4,7
             * teach : 2
             * isSp : 0
             * sper : 2
             * caseId : 1
             * location : 考站三的位置
             * text_core : 核心三
             * state : 0
             */

            private int Id;
            private String name;
            private String addTime;
            private int examier;
            private int min;
            private int max;
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

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
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

        public static class CateBean {
            /**
             * Id : 5
             * describe : 考站san评分类别描述3
             * name : 考站san评分类别2
             */

            private int Id;
            private String describe;
            private String name;
            //打分按钮文字
            private String buttonStr = "打分";

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getButtonStr() {
                return buttonStr;
            }

            public void setButtonStr(String buttonStr) {
                this.buttonStr = buttonStr;
            }
        }

        public static class PointsBean {
            /**
             * Id : 12
             * describe : 优秀
             * min : 0
             * max : 7
             */

            private int Id;
            private String describe;
            private int min;
            private int max;
            //当前选择的分数
            private int index;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }
        }
    }
}
