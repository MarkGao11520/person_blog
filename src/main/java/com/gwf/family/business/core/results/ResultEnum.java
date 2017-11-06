package com.gwf.family.business.core.results;

import lombok.Getter;

/**
 * Created by gaowenfeng on 2017/8/10.
 */
@Getter
public enum  ResultEnum {
    USER_EXISTS(4001,"用户已经存在"),
    USER_NOT_EXISTS(4002,"用户不存在"),
    SAVE_ERROR(4003,"添加失败"),
    ROLE_NOT_EXISTS(4003,"权限不存在"),
    PASSWORD_ERROR(4004,"密码错误"),
    UPDATE_ERROR(4005,"修改失败"),
    PARAM_ERROR(4006,"参数错误")

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
