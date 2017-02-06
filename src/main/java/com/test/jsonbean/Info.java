package com.test.jsonbean;

/**
 * Created by egguncle on 17-2-6.
 */
public class Info {
    private String token;

    private String accid;

    private String name;

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public void setAccid(String accid){
        this.accid = accid;
    }
    public String getAccid(){
        return this.accid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
