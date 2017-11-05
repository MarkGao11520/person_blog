package com.gwf.family.business.bcomment.service.impl;

import com.gwf.family.business.bcomment.dao.BCommentRepository;
import com.gwf.family.business.bcomment.entity.BComment;
import com.gwf.family.business.bcomment.service.BCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BCommentServiceImpl extends AbstractService<BComment> implements BCommentService {
    @Autowired
    private BCommentRepository bCommentRepository;

}
