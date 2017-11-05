package com.gwf.family.business.blog.service.impl;

import com.gwf.family.business.blog.dao.BLogRepository;
import com.gwf.family.business.blog.entity.BLog;
import com.gwf.family.business.blog.service.BLogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-11-5 18:39:48.
 */
@Service
@Transactional
public class BLogServiceImpl extends AbstractService<BLog> implements BLogService {
    @Autowired
    private BLogRepository bLogRepository;

}
