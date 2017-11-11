package com.gwf.family.business.bcomment.dao;

import com.gwf.family.business.bcomment.dto.CommentDTO;
import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.bcomment.entity.BComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BCommentRepository extends Mapper<BComment> {
    @Select("select count(id) from b_comment where blog_id = #{blogId} and target_type = 1")
    int selectCountByBlogId(Integer blogId);

    List<CommentDTO> selectCommentListByBlogId(Integer blogId);

    List<CommentDTO> selectCommentListByTargetId(Integer targetId);
}

