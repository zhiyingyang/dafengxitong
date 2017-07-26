package com.yikaobao.data;

import java.util.List;

/**
 * Created by lx on 2017/6/8.
 */

public class DataStudentsList {


    /**
     * flag : Success
     * msg : null
     * data : {"stationInfo":{"Id":3,"name":"考站三","addTime":"0000-00-00 00:00:00","examier":2,"examinee":"4,7","teach":2,"isSp":0,"sper":2,"caseId":1,"location":"考站三的位置","text_core":"核心三","state":0,"text_case_name":"考案名称","text_case_number":"考案编号"},"userInfo":[{"id":"4","username":"实习小李","phone":"15535730402","employeeNumber":"10004"},{"id":"7","username":"实习小六","phone":"15535730405","employeeNumber":"10007"}]}
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
         * stationInfo : {"Id":3,"name":"考站三","addTime":"0000-00-00 00:00:00","examier":2,"examinee":"4,7","teach":2,"isSp":0,"sper":2,"caseId":1,"location":"考站三的位置","text_core":"核心三","state":0,"text_case_name":"考案名称","text_case_number":"考案编号"}
         * userInfo : [{"id":"4","username":"实习小李","phone":"15535730402","employeeNumber":"10004"},{"id":"7","username":"实习小六","phone":"15535730405","employeeNumber":"10007"}]
         */

        private StationInfoBean stationInfo;
        private List<UserInfoBean> userInfo;

        public StationInfoBean getStationInfo() {
            return stationInfo;
        }

        public void setStationInfo(StationInfoBean stationInfo) {
            this.stationInfo = stationInfo;
        }

        public List<UserInfoBean> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<UserInfoBean> userInfo) {
            this.userInfo = userInfo;
        }

        public static class StationInfoBean {
            /**
             * Id : 3
             * name : 考站三
             * addTime : 0000-00-00 00:00:00
             * examier : 2
             * examinee : 4,7
             * teach : 2
             * isSp : 0
             * sper : 2
             * caseId : 1
             * location : 考站三的位置
             * text_core : 核心三
             * state : 0
             * text_case_name : 考案名称
             * text_case_number : 考案编号
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
            private String text_case_name;
            private String text_case_number;
            private String textTime;
            private String work_unit;


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

            public String getText_case_name() {
                return text_case_name;
            }

            public void setText_case_name(String text_case_name) {
                this.text_case_name = text_case_name;
            }

            public String getText_case_number() {
                return text_case_number;
            }

            public void setText_case_number(String text_case_number) {
                this.text_case_number = text_case_number;
            }

            public String getTextTime() {
                return textTime;
            }

            public void setTextTime(String textTime) {
                this.textTime = textTime;
            }

            public String getWork_unit() {
                return work_unit;
            }

            public void setWork_unit(String work_unit) {
                this.work_unit = work_unit;
            }
        }

        public static class UserInfoBean {
            /**
             * id : 4
             * username : 实习小李
             * phone : 15535730402
             * employeeNumber : 10004
             */

            private String id;
            private String username;
            private String phone;
            private String employeeNumber;
            private int total_score=-1;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmployeeNumber() {
                return employeeNumber;
            }

            public void setEmployeeNumber(String employeeNumber) {
                this.employeeNumber = employeeNumber;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }
        }
    }
}
