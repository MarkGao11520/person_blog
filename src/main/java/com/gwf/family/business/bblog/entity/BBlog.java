package com.gwf.family.business.bblog.entity;

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
@Table(name = "b_blog")
@NoArgsConstructor
@AllArgsConstructor
public class BBlog  implements Serializable{

    /** 博客主键 */
    @Id
    @ApiModelProperty(value = "博客主键")
    private Integer id;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    /** 关键字 */
    @ApiModelProperty(value = "关键字")
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

}

