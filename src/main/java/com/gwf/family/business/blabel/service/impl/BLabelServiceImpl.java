package com.gwf.family.business.blabel.service.impl;

import com.gwf.family.business.blabel.dao.BLabelRepository;
import com.gwf.family.business.blabel.entity.BLabel;
import com.gwf.family.business.blabel.service.BLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BLabelServiceImpl extends AbstractService<BLabel> implements BLabelService {
    @Autowired
    private BLabelRepository bLabelRepository;

}
