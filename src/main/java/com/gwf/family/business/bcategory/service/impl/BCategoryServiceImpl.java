package com.gwf.family.business.bcategory.service.impl;

import com.gwf.family.business.bcategory.dao.BCategoryRepository;
import com.gwf.family.business.bcategory.entity.BCategory;
import com.gwf.family.business.bcategory.service.BCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BCategoryServiceImpl extends AbstractService<BCategory> implements BCategoryService {
    @Autowired
    private BCategoryRepository bCategoryRepository;

}
