package com.gwf.family.business.buserinfo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/5.
 */
@Data
public class UserQueryConditionDto {
    @ApiModelProperty("昵称，模糊查询")
    private String nikeName;
    @ApiModelProperty("手机号，模糊查询")
    private String phone;
    @ApiModelProperty("用户名，模糊查询")
    private String userName;
}
