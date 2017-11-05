package com.gwf.family.business.bvisit.entity;

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
@Table(name = "b_visit")
@NoArgsConstructor
@AllArgsConstructor
public class BVisit  implements Serializable{

    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键")
    private Integer id;
    /** 访客id */
    @ApiModelProperty(value = "访客id")
    private Integer uid;
    /** 博客id */
    @ApiModelProperty(value = "博客id")
    private Integer blogId;
    /** 访客时间 */
    @ApiModelProperty(value = "访客时间")
    private Date visitTime;
    /** 访客id */
    @ApiModelProperty(value = "访客id")
    private String visitIp;

}

