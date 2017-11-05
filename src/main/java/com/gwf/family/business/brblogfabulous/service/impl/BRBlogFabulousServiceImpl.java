package com.gwf.family.business.brblogfabulous.service.impl;

import com.gwf.family.business.brblogfabulous.dao.BRBlogFabulousRepository;
import com.gwf.family.business.brblogfabulous.entity.BRBlogFabulous;
import com.gwf.family.business.brblogfabulous.service.BRBlogFabulousService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BRBlogFabulousServiceImpl extends AbstractService<BRBlogFabulous> implements BRBlogFabulousService {
    @Autowired
    private BRBlogFabulousRepository bRBlogFabulousRepository;

}
