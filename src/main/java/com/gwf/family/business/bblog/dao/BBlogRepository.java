package com.gwf.family.business.bblog.dao;

import com.gwf.family.business.bblog.dto.BlogResponseDTO;
import com.gwf.family.business.bblog.dto.QueryConditionDTO;
import com.gwf.family.business.core.mapper.Mapper;
import com.gwf.family.business.bblog.entity.BBlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Created with gwf on 2017-10-22 14:16:31.
*/
@org.apache.ibatis.annotations.Mapper
public interface BBlogRepository extends Mapper<BBlog> {

    @Insert("insert into b_r_category_blog(category_id,blog_id) values(#{categoryId},#{blogId})")
    int insertBlogCategory(@Param("categoryId") int categoryId,@Param("blogId") int blogId);

    int insertBlogLabelBatch(List<Map<String,Integer>> list);

    int deleteBlogLabelBatchByBlogId(Integer blogId);

    int deleteBlogCategoryBatchByBlogId(Integer blogId);

    List<BlogResponseDTO> selectBlogListByQueryCondition(QueryConditionDTO dto);

    BlogResponseDTO selectBlogByQueryCondition(Integer id);
}

