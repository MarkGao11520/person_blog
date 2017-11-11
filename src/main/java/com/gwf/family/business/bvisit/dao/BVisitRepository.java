package com.gwf.family.business.bvisit.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.bvisit.entity.BVisit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BVisitRepository extends Mapper<BVisit> {

    List<BVisit> selectByBlogId(@Param("blogId") Integer blogId);

    @Select("select count(id) from b_visit where blog_id =#{blogId}")
    int selectCountByBlodId(Integer blogId);
}

