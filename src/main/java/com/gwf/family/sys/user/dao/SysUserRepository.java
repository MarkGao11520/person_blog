package com.gwf.family.sys.user.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.sys.user.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* Created with gwf on 2017-8-10 14:15:19.
*/
@org.apache.ibatis.annotations.Mapper
public interface SysUserRepository extends Mapper<SysUser> {

    @Select("select * from sys_user where username = #{username}")
    @ResultMap("BaseResultMap")
    SysUser findByUserName(String username);

    @Update("update sys_user set password = #{password} where username = #{username}")
    int updatePasswordByUserName(@Param("username")String username,@Param("password")String password);
}

