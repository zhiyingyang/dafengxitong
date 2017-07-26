package com.yikaobao.data;

import java.io.Serializable;

/**
 * Created by lx on 2017/5/18.
 */

public class DataUser implements Serializable {


    /**
     * flag : Success
     * msg : null
     * data : {"id":2,"username":"王医生","phone":"15535730437","employeeNumber":"10002","password":"e10adc3949ba59abbe56e057f20f883e","work_unitId":1,"departmentId":1,"roleId":2,"info":null,"registerTime":"2017-07-10 17:35:24","lastLoginTime":"0000-00-00 00:00:00","loginOutTime":"0000-00-00 00:00:00"}
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


    public static class DataBean implements Serializable {
        /**
         * id : 2
         * username : 王医生
         * phone : 15535730437
         * employeeNumber : 10002
         * password : e10adc3949ba59abbe56e057f20f883e
         * work_unitId : 1
         * departmentId : 1
         * roleId : 2
         * info : null
         * registerTime : 2017-07-10 17:35:24
         * lastLoginTime : 0000-00-00 00:00:00
         * loginOutTime : 0000-00-00 00:00:00
         */

        private int id;
        private String username;
        private String phone;
        private String employeeNumber;
        private String password;
        private int work_unitId;
        private int departmentId;
        private int roleId;
        private Object info;
        private String registerTime;
        private String lastLoginTime;
        private String loginOutTime;
        private String token;


        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getWork_unitId() {
            return work_unitId;
        }

        public void setWork_unitId(int work_unitId) {
            this.work_unitId = work_unitId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLoginOutTime() {
            return loginOutTime;
        }

        public void setLoginOutTime(String loginOutTime) {
            this.loginOutTime = loginOutTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
