package com.gwf.family.business.bblog.service.impl;

import com.gwf.family.business.bblog.dao.BBlogRepository;
import com.gwf.family.business.bblog.entity.BBlog;
import com.gwf.family.business.bblog.service.BBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BBlogServiceImpl extends AbstractService<BBlog> implements BBlogService {
    @Autowired
    private BBlogRepository bBlogRepository;

}
