package com.gwf.family.business.bcomment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.*;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@Entity
@Data
@Table(name = "b_comment")
@NoArgsConstructor
@AllArgsConstructor
public class BComment  implements Serializable{

    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键")
    private Integer id;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /** 博客id */
    @ApiModelProperty(value = "博客id")
    private Integer blogId;
    /** 评论内容 */
    @ApiModelProperty(value = "评论内容")
    private String content;
    /** 评论时间 */
    @ApiModelProperty(value = "评论时间")
    private Date stayTime;
    @ApiModelProperty(value = "评论的目标用户id")
    private Integer targetUid;
    @ApiModelProperty(value = "评论的类型,1博客内容，2首页留言板")
    private Integer targetType;



}

