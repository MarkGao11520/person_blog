package com.gwf.family.sys.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/5.
 */
@Data
public class ChangePasswordDto {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("新密码")
    private String newpassword;
    @ApiModelProperty("旧密码")
    private String oldPassword;
}
