package com.yikaobao.data;

import java.util.List;

/**
 * Created by lx on 2017/5/24.
 */

public class DataHomeTest {


    /**
     * flag : Success
     * msg : null
     * data : [{"Id":1,"name":"考案名称","number":"考案编号","uId":1,"textTime":"2017-07-14","firstdoneTime":"2017-07-09","addTime":"2017-07-06 14:10:22","target":"","work_unitId":1,"beiyong1":0,"togherMeeting":1,"meetingDate":"2017-07-10 14:30:00","meetingPerson":"2,3,4","category":2},{"Id":2,"name":"考案二","number":"考案编号2","uId":1,"textTime":"2017-07-16","firstdoneTime":"2017-07-10","addTime":"2017-07-06 14:34:22","target":"","work_unitId":1,"beiyong1":0,"togherMeeting":0,"meetingDate":"0000-00-00 00:00:00","meetingPerson":"","category":1}]
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
         * name : 考案名称
         * number : 考案编号
         * uId : 1
         * textTime : 2017-07-14
         * firstdoneTime : 2017-07-09
         * addTime : 2017-07-06 14:10:22
         * target :
         * work_unitId : 1
         * beiyong1 : 0
         * togherMeeting : 1
         * meetingDate : 2017-07-10 14:30:00
         * meetingPerson : 2,3,4
         * category : 2
         */

        private int Id;
        private String name;
        private String number;
        private int uId;
        private String textTime;
        private String firstdoneTime;
        private String addTime;
        private String target;
        private int work_unitId;
        private int beiyong1;
        private int togherMeeting;
        private String meetingDate;
        private String meetingPerson;
        private int category;

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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getUId() {
            return uId;
        }

        public void setUId(int uId) {
            this.uId = uId;
        }

        public String getTextTime() {
            return textTime;
        }

        public void setTextTime(String textTime) {
            this.textTime = textTime;
        }

        public String getFirstdoneTime() {
            return firstdoneTime;
        }

        public void setFirstdoneTime(String firstdoneTime) {
            this.firstdoneTime = firstdoneTime;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int getWork_unitId() {
            return work_unitId;
        }

        public void setWork_unitId(int work_unitId) {
            this.work_unitId = work_unitId;
        }

        public int getBeiyong1() {
            return beiyong1;
        }

        public void setBeiyong1(int beiyong1) {
            this.beiyong1 = beiyong1;
        }

        public int getTogherMeeting() {
            return togherMeeting;
        }

        public void setTogherMeeting(int togherMeeting) {
            this.togherMeeting = togherMeeting;
        }

        public String getMeetingDate() {
            return meetingDate;
        }

        public void setMeetingDate(String meetingDate) {
            this.meetingDate = meetingDate;
        }

        public String getMeetingPerson() {
            return meetingPerson;
        }

        public void setMeetingPerson(String meetingPerson) {
            this.meetingPerson = meetingPerson;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }
    }
}
