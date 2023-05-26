package com.yw.msmp.common.result;

/**
 * 列举出本系统业务上的所有的可能情况
 *
 * @author yanhaoyuwen
 */
public enum ResponseEnum {
    /**
     *
     */
    SUCCESS( "200", "成功" ),

    LOGIN_SUCCESS( "200", "登录成功" ),

    USERNAME_NOT_FOUND( "300", "用户名不存在" ),

    USERNAME_OR_PASSWORD_INVALIDATE( "301", "用户名或者密码错误" ),

    HAVE_NO_RIGHTS( "302", "该角色还没有被赋权,请联系管理员" ),

    NO_LOGIN( "303", "还没有登录" ),

    ADD_MOVIE_ERROR( "304", "添加电影异常" ),

    ADD_MOVIE_Category_ERROR( "305", "添加电影类型异常" ),

    DEL_ERROR( "306", "删除异常" ),

    TOKEN_INVALID( "307", "token 无效，请先登录" ),

    TOKEN_EXPIRE( "308", "token 已经超时，请重新登录" ),

    PHONE_FORMAT_ERROR( "309", "手机号格式错误" ),
    CODE_FORMAT_ERROR( "310", "验证码只能是4位数字" ),
    CODE_INVALID( "311", "验证码错误!!" ),

    HAS_NO_ACTIONRIGHT( "413", "当前角色没有此权限" ),

    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL( "310", "短信发送过于频繁" ),

    SMS_SEND_ERROR( "311", "短信发送失败" ),

    UPDATE_ERROR( "312", "更新异常" ),

    ROLE_NO_MENUS( "313", "没有任何权限" ),

    ADD_ERROR( "314", "添加失败" ),

    USER_STATUS_ERROR( "315", "用户已封禁" ),

    Repeating_Field_ERROR( "316", "关键字段重复，请检查" ),

    SCHEDULE_ADD_ERROR( "317", "添加失败，时间存在冲突" ),

    BUY_TICKET_LATE_ERROR( "318", "票已被购买" ),

    BUY_TICKET_ERROE( "319", "购买异常" ),

    SCHEDULE_LATE_ERROR( "320", "该场次已经开始" ),

    SYSTEM_ERROR( "500", "发生未知错误，请联系管理员" ),

    ADD_DRUG_ERROR( "601", "该药品详情已被禁用" ),

    OPERATE_ERROR( "602", "操作异常，请重新操作" ),

    ADD_BATCH_ERROR( "603", "请检查批次状态是否可用" ),

    CHECK_ERROR( "606", "检查异常，请重试" ),

    NO_PERMISSIONS( "701", "没有权限，请联系管理员" ),

    PLEASE_WAIT_DELIVERY_ARRIVE( "702", "请到达配送到达" ),

    INVENTORY_SHORTAGE_ERROR( "703", "库存不足" ),

    PAY_ERROR( "707", "支付异常" ),

    PAY_OVER_ERROR( "708", "支付完成，请勿频繁点击" ),

    PAY_NULL_ERROR( "709", "未支付，请支付" ),

    PAY_FINISH_ERROR( "710", "支付完成，跳转" ),

    PAY_REFUND_ERROR( "711", "退款失败" ),

    PHONE_REPEAT_ERROR( "712", "手机号已经被使用，请更换" ),

    NAME_REPEAT_ERROR( "713", "名称重复错误" ),

    PERMISSION_NOT_SET_ERROR( "714", "权限未设置，请联系管理员，增加权限" );
    private String code;
    private String message;

    // 不能在枚举的外面 去创建枚举的对象
    ResponseEnum( String code, String message ) {
        this.code = code;
        this.message = message;
    }

    public String getCode( ) {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getMessage( ) {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

}
