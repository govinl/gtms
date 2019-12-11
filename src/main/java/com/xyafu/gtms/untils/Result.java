package com.xyafu.gtms.untils;

import java.io.Serializable;

/**
 * @author 刘高伟
 * @Classname Result
 * @Description TODO
 * @Date 2019/9/28 16:00
 */
public class Result implements Serializable {
    /**
     * 返回状态标识：true成功 false失败
     */
    public boolean res;
    /**
     * 成功或者错误消息
     */
    public String msg;
    /**
     * 返回数据
     */
    public Object data;

    /**
     * 返回链接
     */
    public String url;

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 构造方法
     * @return
     */
    public Result(){

    }

    /**
     * 构造方法
     * @return
     */
    public static Result success(){
        Result result=new Result();
        result.setRes(true);
        return result;
    }

    /**
     * 构造方法
     * @return
     */
    public static Result success(Object data){
        Result result=new Result();
        result.setRes(true);
        result.setData(data);
        return result;
    }

    /**
     * 构造方法
     * @return
     */
    public static Result success(String msg,Object data){
        Result result=new Result();
        result.setRes(true);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 构造方法
     * @return
     */
    public static Result success(String msg,Object data,String url){
        Result result=new Result();
        result.setRes(true);
        result.setMsg(msg);
        result.setData(data);
        result.setUrl(url);
        return result;
    }

    public static Result fail(){
        Result result=new Result();
        result.setRes(false);
        return result;
    }

    /**
     * 构造方法
     * @return
     */
    public static Result fail(String msg){
        Result result=new Result();
        result.setRes(false);
        result.setMsg(msg);
        return result;
    }

    /**
     * 构造方法
     * @return
     */
    public static Result fail(String msg,Object data){
        Result result=new Result();
        result.setRes(false);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    /**
     * 构造方法
     * @return
     */
    public static Result fail(String msg,Object data,String url){
        Result result=new Result();
        result.setRes(false);
        result.setMsg(msg);
        result.setData(data);
        result.setUrl(url);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "res=" + res +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", url='" + url + '\'' +
                '}';
    }
}
