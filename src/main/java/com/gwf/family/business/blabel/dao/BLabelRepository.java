package com.gwf.family.business.blabel.dao;

import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.blabel.entity.BLabel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BLabelRepository extends Mapper<BLabel> {
    @Select("select * from b_label l left join b_r_blog_label bl on l.id = bl.label_id where bl.blog_id=#{blogId}")
    List<BLabel> selectByBlogId(Integer blogId);
}

