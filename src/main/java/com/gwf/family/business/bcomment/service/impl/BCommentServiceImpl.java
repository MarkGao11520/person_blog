package com.gwf.family.business.bcomment.service.impl;

import com.gwf.family.business.bcomment.dao.BCommentRepository;
import com.gwf.family.business.bcomment.dto.CommentDTO;
import com.gwf.family.business.bcomment.entity.BComment;
import com.gwf.family.business.bcomment.service.BCommentService;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BCommentServiceImpl extends AbstractService<BComment> implements BCommentService {
    @Autowired
    private BCommentRepository bCommentRepository;

    @Override
    public List<CommentDTO> findByBlogId(Integer blogId) {
        return bCommentRepository.selectCommentListByBlogId(blogId);
    }

    @Override
    public void deleteById(Object id) {
        List<CommentDTO> list = bCommentRepository.selectCommentListByTargetId((Integer) id);
        for(CommentDTO commentDTO:list){
            deleteById(commentDTO.getId());
        }
        bCommentRepository.deleteByPrimaryKey(id);
    }
}
