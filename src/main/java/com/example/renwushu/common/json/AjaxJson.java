package com.example.renwushu.common.json;


import lombok.Data;
import lombok.experimental.Accessors;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class AjaxJson<T> implements Serializable {
    public static final StatusCode CODE_COMMON_FAIL;
    private static final long serialVersionUID = -7790919285662399570L;
    private boolean success = true;
    private String code;
    private String msg;
    private Map<String, T> body;
    private T data;

    public AjaxJson() {
        this.code = StatusCode.SUCESS.statusCode;
        this.msg = StatusCode.SUCESS.statusDesc;
        this.body = new LinkedHashMap();
    }

    public static AjaxJson returnInfo(StatusCode statusCode, Boolean success) {
        AjaxJson json = new AjaxJson();
        json.setSuccess(success);
        json.setCode(statusCode.statusCode);
        json.setMsg(statusCode.statusDesc);
        return json;
    }

    public static AjaxJson returnExceptionInfo(String msg) {
        AjaxJson json = new AjaxJson();
        json.setSuccess(false);
        json.setCode(CODE_COMMON_FAIL.statusCode);
        json.setMsg(msg);
        return json;
    }

    public static AjaxJson returnExceptionInfo(String code, String msg) {
        AjaxJson json = new AjaxJson();
        json.setSuccess(false);
        json.setCode(code);
        json.setMsg(msg);
        return json;
    }

    public static AjaxJson returnExceptionInfo(StatusCode statusCode) {
        AjaxJson json = new AjaxJson();
        json.setSuccess(false);
        json.setCode(statusCode.statusCode);
        json.setMsg(statusCode.statusDesc);
        return json;
    }

    public AjaxJson(StatusCode statusCode, Boolean success, String msg) {
        this.body = new LinkedHashMap();
        this.code = statusCode.statusCode;
        this.success = success;
        this.msg = msg;
    }

    public void setStatusCode(StatusCode statusCode){
        this.setCode(statusCode.statusCode);
        this.setMsg(statusCode.statusDesc);
    }

    public String toString() {
        return "AjaxJson [success=" + this.success + ", code=" + this.code + ", msg=" + this.msg + "]";
    }
    public JSONObject toJsonObjec() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",this.success);
        jsonObject.put("code",this.code);
        jsonObject.put("msg",this.msg);
        return jsonObject;
    }

    static {
        CODE_COMMON_FAIL = StatusCode.FAIL;
    }
}
