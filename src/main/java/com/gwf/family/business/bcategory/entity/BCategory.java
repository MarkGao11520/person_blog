package com.gwf.family.business.bcategory.entity;

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
@Table(name = "b_category")
@NoArgsConstructor
@AllArgsConstructor
public class BCategory  implements Serializable{

    /** 主键 */
    @Id
    @ApiModelProperty(value = "主键,添加不用填写")
    private Integer id;
    /** 分类 */
    @ApiModelProperty(value = "分类")
    private String name;

}

