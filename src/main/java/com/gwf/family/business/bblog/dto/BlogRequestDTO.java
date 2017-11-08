package com.gwf.family.business.bblog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gaowenfeng on 2017/11/7.
 */
@Data
public class BlogRequestDTO {
    /** 关键字 */
    @ApiModelProperty(value = "关键字，逗号隔开")
    private String keyword;
    /** 标题 */
    @ApiModelProperty(value = "标题")
    private String title;
    /** 内容 */
    @ApiModelProperty(value = "内容")
    private String content;
    /** 封面url */
    @ApiModelProperty(value = "封面url")
    private String coverUrl;
    @ApiModelProperty(value = "标签id，多个的逗号隔开")
    private String labelIds;
    @ApiModelProperty(value = "分类id")
    private Integer categoryId;
}
