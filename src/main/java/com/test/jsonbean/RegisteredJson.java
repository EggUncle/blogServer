package com.test.jsonbean;

/**
 * Created by egguncle on 17-2-6.
 *
 * 注册网易云信帐号时返回的json数据
 *
 */
public class RegisteredJson {
    //状态码
    private int code;

    private Info info;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setInfo(Info info){
        this.info = info;
    }
    public Info getInfo(){
        return this.info;
    }
}
