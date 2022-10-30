package com.example.renwushu.common.json;

public enum StatusCode {
    SUCESS("100", "操作成功"),
    FAIL("101", "操作失败"),


    USER_UNREGISTERED("103", "用户未注册"),
    USER_ACCOUNT_EXISTED("104", "账号已经存在"),
    USER_NAME_EXISTED("105", "人员名称已经存在"),
    USER_UNLOGIN("106", "用户未登录"),
    USER_LOGIN_SUCESS("107", "登录成功"),
    USER_LOGIN_FAIL("108", "登录失败，用户或密码错误"),
    USER_PASSWORD_FAIL("108", "登录失败，密码错误"),
    USER_NAME_FAIL("108", "登录失败，用户名错误"),
    USER_FORBID("109", "用户被禁止登录"),
    USER_ILLEGAL_AUTH("110", "非法授权"),
    USER_TOKEN_ERRO("111", "TOKEN验证错误"),
    USER_TOKEN_INVALID("112", "TOKEN验证失效"),

    USER_SESSION_ERRO("113", "session验证错误"),
    USER_SESSION_INVALID("114", "session验证失效"),


    EXCEPTION_SYSTEM("500", "系统异常"),
    EXCEPTION_UNKNOWN("501", "未知异常,请联系管理员"),

    APP_ORG_ASSIGN("114", "应用组织权限已分配"),
    PRIMARY_KEY_ISNULL("115", "主键为空"),
    APP_IS_EXIST("116", "保存失败，应用已存在"),
    SAVE_EXCEPTION("117", "保存异常，请联系管理员"),
    UPDATE_EXCEPTION("118", "修改异常，请联系管理员"),
    DELETE_EXCEPTION("119", "删除异常，请联系管理员"),
    DISTRIBUTE_RESOURCES_EXCEPTION("120", "分配资源异常，请联系管理员"),
    APP_ID_ISNULL("121", "应用id为空"),
    CONTEXT_IS_EXIST("122", "保存失败，上下文配置已存在"),
    RESOURCETYPE_IS_EXIST("123", "保存失败，资源类别已存在"),
    MENU_IS_EXIST("124", "保存失败，菜单已存在"),
    DICT_IS_EXIST("125", "保存失败，字典已存在"),
    DICTDATA_IS_EXIST("126", "保存失败，字典项已存在"),
    MENULAYOUT_IS_EXIST("127", "保存失败，菜单布局已存在"),
    SKIN_IS_EXIST("128", "保存失败，皮肤设置已存在"),
    RESOURCETYPE_OPERATION_IS_EXIST("129", "保存失败，资源类别操作已存在"),
    ORG_NAME_IS_EXIST("130", "组织已存在"),
    APPLICATION_APP_CODE_ERRO("131", "appCode无效"),
    APPLICATION_APP_ID_CODE_SIMULTANEOUSLYEXIST_ERRO("131", "appId和appCode不能同时存在"),
    ROLE_ID_CODE_PARAM_ISNULL("132", "角色Id和角色编码不能同时存在也不能同时为空"),
    ROLE_IS_NOT_NULL("133", "用户至少需要有一个角色"),
    FILE_PATH_ISNULL("140", "文件路径为空"),
    FILE_ISNULL("141", "文件为空"),
    FILE_UPLOAD_FAILED("142", "文件上传失败"),
    FILE_NOT_EXIST("143", "文件不存在"),
    FILE_DOWNLOAD_FAILED("144", "文件下载失败"),
    FILE_DELETE_FAILED("145", "删除文件失败"),
    FILE_SERVER_CONNECTION_FAILED("146", "文件服务器连接失败"),
    FILE_OUT_SIZE("147", "文件超过大小"),
    FILE_TYPE_ERROR_IMAGE("148", "图片类型错误"),
    FILE_TYPE_ERROR_DOC("149", "文档类型错误"),
    FILE_TYPE_ERROR_VIDEO("140", "音频类型错误"),
    FILE_TYPE_ERROR_COMPRESS("141", "压缩文件类型错误"),
    FILE_TYPE_ERROR_ALLOW("142", "不是允许的文件类型"),
    REDIS_LINK_FAIL("145", "redis连接异常"),
    GRP_ADD_ORG_FAIL("146", "该组织已添加"),
    GRP_ADD_PERSON_FAIL("147", "该用户已添加"),
    GRP_NOT_EXIST("148", "用户组不存在"),
    GRP_NAME_EXISTED("149", "用户组名称已存在"),
    CAS_URI_RESOLVE_ERROR("151", "CAS AccessToken换取用户信息的回调URI解析错误"),
    CAS_REDIRECT_URL_NOT_USERNAME_ERROR("152", "CAS AccessToken换取用户信息的回调URI没有用户登录名称"),
    CAS_USER_AUTH_FAIL("153", "用户认证失败, 用户未登录或登录过期"),
    CAS_USER_TOKEN_NOT_FOUND("154", "AccessToken 头部参数没找到"),
    CAS_AUTHENTICATION_FAIL("155", "无权限访问该资源"),
    CAS_AUTHENTICATION_NOT_APPID_FAIL("156", "鉴权失败,缓存中没有当前用户操作的应用id"),
    CAS_NOT_IN_APPOINT_AREA("157", "不在指定区域，无法登录"),
    CAS_LOGIN_FAIL("158", "登录失败"),
    CAS_PARAMS_ERROR("159", "参数错误"),
    CURRENT_USER_ERROR("160", "无用户信息"),
    POLICY_PASSWORD_SUCCESS("161", "密码格式正确"),
    POLICY_PASSWORD_LENGTH_ERR("162", "密码长度错误"),
    POLICY_PASSWORD_MIXLEVEL_ERR("163", "密码格式错误"),
    POLICY_PASSWORD_NOT_NULL("164", "密码不能为空"),
    APP_MOBILE_OLD_PASSWORD_ERR("165", "旧密码错误"),
    ORG_CODE_EXISTED("166", "组织编码已存在");

    public String statusCode;
    public String statusDesc;

    private StatusCode(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }
}
