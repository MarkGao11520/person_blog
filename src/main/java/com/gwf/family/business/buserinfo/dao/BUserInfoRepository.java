package com.gwf.family.business.buserinfo.dao;

import com.gwf.family.business.buserinfo.dto.UserQueryConditionDto;
import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.buserinfo.entity.BUserInfo;

import java.util.List;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BUserInfoRepository extends Mapper<BUserInfo> {
    /**
     * 根据条件查询用户列表
     * @param dto
     * @return
     */
    List<BUserInfo> selectByUserQueryCondition(UserQueryConditionDto dto);
}

