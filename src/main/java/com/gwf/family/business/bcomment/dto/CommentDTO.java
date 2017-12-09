package com.gwf.family.business.bcomment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/11/11.
 */
@Data
public class CommentDTO {
    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键")
    private Integer id;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    @ApiModelProperty(value = "用户名")
    private String uname;
    /** 博客id */
    @ApiModelProperty(value = "博客id")
    private Integer blogId;
    /** 评论内容 */
    @ApiModelProperty(value = "评论内容")
    private String content;
    /** 评论时间 */
    @ApiModelProperty(value = "评论时间")
    private Date stayTime;
    @ApiModelProperty(value = "评论的目标id")
    private Integer targetId;
    @ApiModelProperty(value = "评论的类型,1博客内容，2首页留言板")
    private Integer targetType;
    @ApiModelProperty(value = "点赞数")
    private Integer favCount;
    @ApiModelProperty(value = "评论子列表")
    private List<CommentDTO> commentDTOChildList;
}
