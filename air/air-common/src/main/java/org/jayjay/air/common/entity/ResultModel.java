package org.jayjay.air.common.entity;

import org.jayjay.air.common.constant.ResultCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author jayjay
 * @date 2019/11/1418:46
 * @Description: 返回类
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 5858874322899422740L;

    private static final Integer successCode = 200;
    private static final String success = "success";
    private static final String successMsg = "成功";
    private static final Integer errorCode = 500;
    private static final String error = "error";
    private static final String errorMsg = "未知异常，请联系管理员";


    /**
     * 请求标识
     */
    private String status = success;

    /**
     * 状态码，默认为失败状态
     */
    private Integer code = ResultCode.SUCCESS.getCode();

    /***
     * 操作信息
     */
    private String msg;

    /**
     * 返回对象数据
     */
    private T data;



    /**
     * 成功响应
     */
    public void OK() {
        this.status = success;
        this.code = ResultCode.SUCCESS.getCode();
    }

    /**
     * 请求成功，但业务逻辑处理不通过
     */
    public void NO() {
        this.status = success;
        this.code = ResultCode.ERROR.getCode();
    }

    public ResultModel() {
        super();
    }

    public ResultModel(int code, String msg) {
        super();
        this.status = success;
        this.code = code;
        this.msg = msg;
    }


    public ResultModel(int code, String msg, T t) {
        super();
        this.status = success;
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setObject(T object) {
        this.data = object;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonVo [status=" + status + ", code=" + code + ", msg=" + msg + ", obj=" + data + "]";
    }

    public static ResultModel success() {
        ResultModel resultModel = new ResultModel();
        resultModel.code = ResultCode.SUCCESS.getCode();
        resultModel.msg = ResultCode.SUCCESS.getMsg();
        resultModel.status = success;
        return resultModel;
    }

    public static ResultModel success(String msg) {
        ResultModel resultModel = new ResultModel();
        resultModel.code = ResultCode.SUCCESS.getCode();
        resultModel.msg = msg;
        resultModel.status = success;
        return resultModel;
    }

    public static ResultModel success(List list) {
        ResultModel resultModel = new ResultModel(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), list);
        return resultModel;
    }

    public static ResultModel success(String msg, List list) {
        ResultModel resultModel = new ResultModel(ResultCode.SUCCESS.getCode(), msg, list);
        return resultModel;
    }

    public static ResultModel success(String msg, Object obj) {
        ResultModel resultModel = new ResultModel(ResultCode.SUCCESS.getCode(), msg, obj);
        return resultModel;
    }

    public static ResultModel success(Integer code, String msg, List list) {
        ResultModel resultModel = new ResultModel(code, msg, list);
        return resultModel;
    }

    public static ResultModel success(Integer code, String msg, Object t) {
        ResultModel resultModel = new ResultModel(code, msg, t);
        return resultModel;
    }

    public static ResultModel success(Object t) {
        ResultModel resultModel = new ResultModel(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), t);
        return resultModel;
    }

    public static ResultModel error() {
        ResultModel resultModel = new ResultModel();
        resultModel.code = ResultCode.ERROR.getCode();
        resultModel.msg = ResultCode.ERROR.getMsg();
        resultModel.status = error;
        return resultModel;
    }

    public static ResultModel error(String msg) {
        ResultModel resultModel = new ResultModel();
        resultModel.code = ResultCode.ERROR.getCode();
        resultModel.msg = msg;
        resultModel.status = error;
        return resultModel;
    }

    public static ResultModel error(Integer code, String msg) {
        ResultModel resultModel = new ResultModel();
        resultModel.code = code;
        resultModel.msg = msg;
        resultModel.status = error;
        return resultModel;
    }

    public static ResultModel error(Integer code, String msg, Object t) {
        ResultModel resultModel = new ResultModel();
        resultModel.code = code;
        resultModel.msg = msg;
        resultModel.data = t;
        resultModel.status = error;
        return resultModel;
    }

}
