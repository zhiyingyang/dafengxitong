package com.yikaobao.data;

import java.util.List;

/**
 * Created by pc on 2017/8/2.
 */

public class DataQuestionId {


    /**
     * flag : Success
     * msg : null
     * data : {"Id":1,"caseId":2,"title":"考案二问卷调查表","addTime":"2017-08-01 15:49:50","message":"第一次做问卷调查","info":[{"questionId":1,"title":"单选题","type":1,"option":[{"optionId":1,"optionName":"单选选项一"},{"optionId":2,"optionName":"单选选项二"},{"optionId":3,"optionName":"单选选项三"}]},{"questionId":2,"title":"多选题","type":2,"option":[{"optionId":4,"optionName":"多选选项一"},{"optionId":5,"optionName":"多选选项二"},{"optionId":6,"optionName":"多选选项三"},{"optionId":7,"optionName":"多选选项四"}]},{"questionId":3,"title":"文本提","type":3},{"questionId":4,"title":"排序","type":4,"option":[{"optionId":8,"optionName":"排序选项一"},{"optionId":9,"optionName":"排序选项二"},{"optionId":10,"optionName":"排序选项三"}]}]}
     */

    private String flag;
    private Object msg;
    private DataBean data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
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
         * Id : 1
         * caseId : 2
         * title : 考案二问卷调查表
         * addTime : 2017-08-01 15:49:50
         * message : 第一次做问卷调查
         * info : [{"questionId":1,"title":"单选题","type":1,"option":[{"optionId":1,"optionName":"单选选项一"},{"optionId":2,"optionName":"单选选项二"},{"optionId":3,"optionName":"单选选项三"}]},{"questionId":2,"title":"多选题","type":2,"option":[{"optionId":4,"optionName":"多选选项一"},{"optionId":5,"optionName":"多选选项二"},{"optionId":6,"optionName":"多选选项三"},{"optionId":7,"optionName":"多选选项四"}]},{"questionId":3,"title":"文本提","type":3},{"questionId":4,"title":"排序","type":4,"option":[{"optionId":8,"optionName":"排序选项一"},{"optionId":9,"optionName":"排序选项二"},{"optionId":10,"optionName":"排序选项三"}]}]
         */

        private int Id;
        private int caseId;
        private String title;
        private String addTime;
        private String message;


        private List<InfoBean> info;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getCaseId() {
            return caseId;
        }

        public void setCaseId(int caseId) {
            this.caseId = caseId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }



        public static class InfoBean{
            /**
             * questionId : 1
             * title : 单选题
             * type : 1
             * option : [{"optionId":1,"optionName":"单选选项一"},{"optionId":2,"optionName":"单选选项二"},{"optionId":3,"optionName":"单选选项三"}]
             */

            private int questionId;
            private String title;
            private int type;
            private List<OptionBean> option;

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }



            //选项

            public static class OptionBean {
                /**
                 * optionId : 1
                 * optionName : 单选选项一
                 */

                private int optionId;
                private String optionName;

                public int getOptionId() {
                    return optionId;
                }

                public void setOptionId(int optionId) {
                    this.optionId = optionId;
                }

                public String getOptionName() {
                    return optionName;
                }

                public void setOptionName(String optionName) {
                    this.optionName = optionName;
                }
            }
        }
    }


}
