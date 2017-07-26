package com.yikaobao.data;

import com.yikaobao.base.BaseApplication;

import java.util.HashMap;

/**
 * Created by lx on 2017/7/17.
 */

public class RequestParams extends HashMap {
    public RequestParams() {

        if (BaseApplication.user != null) {
            this.put("token", BaseApplication.user.getData().getToken());
            this.put("userId", BaseApplication.user.getData().getId());
        }
        if (BaseApplication.caseId > Integer.MIN_VALUE) this.put("caseId", BaseApplication.caseId);
    }

}
