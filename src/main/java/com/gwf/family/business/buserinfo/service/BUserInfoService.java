package com.gwf.family.business.buserinfo.service;
import com.gwf.family.business.buserinfo.dto.UserQueryConditionDto;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import com.gwf.family.business.core.service.Service;

import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
public interface BUserInfoService extends Service<BUserInfo> {

    /**
     * 根据条件查询用户列表
     * @param userQueryConditionDto
     * @return
     */
    List<BUserInfo> findByUserQueryCondition(UserQueryConditionDto userQueryConditionDto);
}
