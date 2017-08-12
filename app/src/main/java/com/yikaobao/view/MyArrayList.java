package com.yikaobao.view;

import java.util.ArrayList;

/**
 * Created by pc on 2017/8/3.
 */

public class MyArrayList extends ArrayList {
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.size(); i++) {
            stringBuffer.append(this.get(i).toString());
            stringBuffer.append(",");
        }
        return stringBuffer.toString().substring(0,stringBuffer.length()-1);
    }
}
