package com.gwf.family.sys.user.entity;

import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@Entity
@Data
@Table(name = "sys_user")
public class SysUser  implements Serializable{
    private static final long serialVersionUID = 4857621004791624053L;

    public SysUser(){
    }

    public SysUser(Integer id,String password,String username){
        this.id=id;
        this.password=password;
        this.username=username;
    }

    /** id */
    @Id
    @ApiModelProperty("id，登录不需要")
    private Integer id;
    /** 密码 */
    @ApiModelProperty("密码")
    private String password;
    /** 用户名 */
    @ApiModelProperty("用户")
    private String username;

    /**权限列表**/
    @Transient
    @ApiModelProperty("权限列表，例如[{\"id\":1,\"name\":\"ROLE_ADMIN\"}],默认为用户权限")
    private List<SysRole> roles;

}

