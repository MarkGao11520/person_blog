package com.gwf.family.business.brblogfabulous.entity;

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
@Table(name = "b_r_blog_fabulous")
@NoArgsConstructor
@AllArgsConstructor
public class BRBlogFabulous  implements Serializable{

    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键")
    private Integer id;
    /** 博客id */
    @ApiModelProperty(value = "博客id")
    private Integer blogId;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /** 点赞时间 */
    @ApiModelProperty(value = "点赞时间")
    private Date fabulousTime;

}

