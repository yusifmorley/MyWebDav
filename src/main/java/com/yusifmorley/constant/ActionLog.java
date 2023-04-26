package com.yusifmorley.constant;


import java.io.IOError;
import java.io.IOException;

//日志中的 活动 字段
public enum ActionLog {
    PathIsNotExist("此路径不存在"),
    PathIsNotDirectory("此路径不是目录");
    private String  msg;
    ActionLog(String msg){
    this.msg=msg;
}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
