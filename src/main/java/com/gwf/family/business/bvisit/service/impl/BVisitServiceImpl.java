package com.gwf.family.business.bvisit.service.impl;

import com.gwf.family.business.bvisit.dao.BVisitRepository;
import com.gwf.family.business.bvisit.entity.BVisit;
import com.gwf.family.business.bvisit.service.BVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BVisitServiceImpl extends AbstractService<BVisit> implements BVisitService {
    @Autowired
    private BVisitRepository bVisitRepository;

}
