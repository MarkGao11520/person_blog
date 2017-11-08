package com.gwf.family.business.bblog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by gaowenfeng on 2017/11/8.
 */
@Data
public class QueryConditionDTO {
    @ApiModelProperty("查询关键字，模糊搜索")
    private String keyWord;
    @ApiModelProperty("查询标签，多个逗号隔开，没有传null")
    private String labelIds;
    @ApiModelProperty("分类id")
    private Integer categoryId;
}
