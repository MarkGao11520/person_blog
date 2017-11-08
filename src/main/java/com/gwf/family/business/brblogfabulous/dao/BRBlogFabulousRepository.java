package com.gwf.family.business.brblogfabulous.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.brblogfabulous.entity.BRBlogFabulous;
import org.apache.ibatis.annotations.Select;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BRBlogFabulousRepository extends Mapper<BRBlogFabulous> {

    @Select("select count(id) from b_r_blog_fabulous where blog_id = #{blogId} and target_type = 1")
    int selectCountByBlogId(Integer blogId);
}

