package com.gwf.family.business.bcomment.service;
import com.gwf.family.business.bcomment.dto.CommentDTO;
import com.gwf.family.business.bcomment.entity.BComment;
import com.gwf.family.business.core.service.Service;

import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
public interface BCommentService extends Service<BComment> {

    List<CommentDTO> findByBlogId(Integer blogId);
}
