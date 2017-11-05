package com.gwf.family.business.buserinfo.entity;

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
@Table(name = "b_user_info")
@NoArgsConstructor
@AllArgsConstructor
public class BUserInfo  implements Serializable{

    /** user id */
    @Id
    @ApiModelProperty(value = "user id")
    private Integer id;
    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    private String nikeName;
    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /** qq号 */
    @ApiModelProperty(value = "qq号")
    private String qq;
    /** 微信 */
    @ApiModelProperty(value = "微信")
    private String wechat;
    /** 性别,0男1女 */
    @ApiModelProperty(value = "性别,0男1女")
    private Boolean sex;
    /** 头像url */
    @ApiModelProperty(value = "头像url")
    private String headPic;
    /** 0未锁定1锁定 */
    @ApiModelProperty(value = "0未锁定1锁定")
    private Boolean isLock;

}

