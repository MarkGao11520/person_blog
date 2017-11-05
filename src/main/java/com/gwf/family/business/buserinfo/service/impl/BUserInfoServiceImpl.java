package com.gwf.family.business.buserinfo.service.impl;

import com.gwf.family.business.buserinfo.dao.BUserInfoRepository;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import com.gwf.family.business.buserinfo.service.BUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BUserInfoServiceImpl extends AbstractService<BUserInfo> implements BUserInfoService {
    @Autowired
    private BUserInfoRepository bUserInfoRepository;

}
