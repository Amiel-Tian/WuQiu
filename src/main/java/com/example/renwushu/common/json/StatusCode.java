package com.example.renwushu.common.json;

public enum StatusCode {
    SUCESS("100", "操作成功"),
    FAIL("101", "操作失败"),


    USER_UNREGISTERED("103", "用户未注册"),
    USER_ACCOUNT_EXISTED("104", "账号已经存在"),
    USER_NAME_EXISTED("105", "人员名称已经存在"),
    USER_UNLOGIN("106", "用户未登录"),
    USER_ILLEGAL_AUTH("107", "非法授权"),

    USER_LOGIN_SUCESS("110", "登录成功"),
    USER_LOGIN_FAIL("111", "登录失败，用户或密码错误"),
    USER_LOGIN_PASSWORD_FAIL("112", "登录失败，密码错误"),
    USER_LOGIN_NAME_FAIL("113", "登录失败，用户名错误"),
    USER_LOGIN_FORBID("114", "用户被禁止登录"),
    USER_LOGINOUT_SUCESS("115", "退出登录成功"),

    USER_TOKEN_ERRO("120", "TOKEN验证错误"),
    USER_TOKEN_INVALID("121", "TOKEN验证失效"),

    USER_SESSION_ERRO("130", "session验证错误"),
    USER_SESSION_INVALID("131", "session验证失效"),

    AUTHENTICATION_FAIL("300","身份验证失败"),

    EXCEPTION_SYSTEM("500", "系统异常"),
    EXCEPTION_UNKNOWN("501", "未知异常,请联系管理员"),

    ;

    public String statusCode;
    public String statusDesc;

    private StatusCode(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }
}
