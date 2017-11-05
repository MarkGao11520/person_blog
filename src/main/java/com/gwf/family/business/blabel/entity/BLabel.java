package com.gwf.family.business.blabel.entity;

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
@Table(name = "b_label")
@NoArgsConstructor
@AllArgsConstructor
public class BLabel  implements Serializable{

    /** 标签主键 */
    @Id
    @ApiModelProperty(value = "标签主键")
    private Integer id;
    /** 标签名称 */
    @ApiModelProperty(value = "标签名称")
    private String name;

}

