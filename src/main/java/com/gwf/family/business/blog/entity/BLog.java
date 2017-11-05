package com.gwf.family.business.blog.entity;

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
* Created with gwf on 2017-11-5 18:39:48.
*/
@Entity
@Data
@Table(name = "b_log")
@NoArgsConstructor
@AllArgsConstructor
public class BLog  implements Serializable{

    /** id */
    @Id
    @ApiModelProperty(value = "id")
    private Integer id;
    /** 日志id(为评论记录的id或点赞记录的id) */
    @ApiModelProperty(value = "日志id(为评论记录的id或点赞记录的id)")
    private Integer logId;
    /** 1:评论 ;2: 点赞 */
    @ApiModelProperty(value = "1:评论 ;2: 点赞")
    private Integer type;
    /** 日志时间 */
    @ApiModelProperty(value = "日志时间")
    private Date logTime;
    /** 0: unread; 1: read */
    @ApiModelProperty(value = "0: unread; 1: read")
    private Integer readStatus;

}

