package com.gwf.family.business.bblog.service.impl;

import com.gwf.family.business.bblog.dao.BBlogRepository;
import com.gwf.family.business.bblog.dto.BlogRequestDTO;
import com.gwf.family.business.bblog.dto.BlogResponseDTO;
import com.gwf.family.business.bblog.dto.QueryConditionDTO;
import com.gwf.family.business.bblog.entity.BBlog;
import com.gwf.family.business.bblog.service.BBlogService;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.common.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BBlogServiceImpl extends AbstractService<BBlog> implements BBlogService {
    @Autowired
    private BBlogRepository bBlogRepository;

    @Override
    @Transactional
    public void saveDto(BlogRequestDTO dto) {
        BBlog blogToAdd = new BBlog();
        BeanUtils.copyProperties(dto,blogToAdd);
        int result = bBlogRepository.insertSelective(blogToAdd);
        if(result<1)
            throw new ServiceException(ResultEnum.SAVE_ERROR);
        result = bBlogRepository.insertBlogCategory(dto.getCategoryId(),blogToAdd.getId());

        if(result<1)
            throw new ServiceException(ResultEnum.SAVE_ERROR);

        List<Map<String,Integer>> list = new ArrayList<>();
        String[] ids = CommonUtil.spiltString(dto.getLabelIds(),",");
        for(String id:ids) {
            Map<String, Integer> map = new HashMap<>();
            map.put("blogId", blogToAdd.getId());
            try {
                map.put("labelId", Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ResultEnum.PARAM_ERROR);
            }
            list.add(map);
        }
        result = bBlogRepository.insertBlogLabelBatch(list);
        if(result!=list.size())
            throw new ServiceException(ResultEnum.SAVE_ERROR);
    }

    @Override
    public List<BlogResponseDTO> findListByQueryConditionDto(QueryConditionDTO dto) {
        if(!StringUtils.isEmpty(dto.getLabelIds()))
            dto.setLabelIds("("+dto.getLabelIds()+")");
        else
            dto.setLabelIds(null);
        if(StringUtils.isEmpty(dto.getKeyWord()))
            dto.setKeyWord(null);
        return bBlogRepository.selectBlogListByQueryCondition(dto);
    }

    @Override
    @Transactional
    public void updateDto(Integer id,BlogRequestDTO dto) {
        BBlog blogToUpdate = new BBlog();
        BeanUtils.copyProperties(dto,blogToUpdate);
        blogToUpdate.setId(id);
        int result = bBlogRepository.updateByPrimaryKeySelective(blogToUpdate);
        if(result!=1)
            throw new ServiceException(ResultEnum.UPDATE_ERROR);

        if(!StringUtils.isEmpty(dto.getCategoryId())){
            bBlogRepository.deleteBlogCategoryBatchByBlogId(id);
            result = bBlogRepository.insertBlogCategory(dto.getCategoryId(),id);
            if(result<1)
                throw new ServiceException(ResultEnum.SAVE_ERROR);
        }
        if(!StringUtils.isEmpty(dto.getLabelIds())){
            bBlogRepository.deleteBlogLabelBatchByBlogId(id);
            List<Map<String,Integer>> list = new ArrayList<>();
            String[] ids = CommonUtil.spiltString(dto.getLabelIds(),",");
            for(String labelId:ids) {
                Map<String, Integer> map = new HashMap<>();
                map.put("blogId", id);
                try {
                    map.put("labelId", Integer.parseInt(labelId));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException(ResultEnum.PARAM_ERROR);
                }
                list.add(map);
            }
            result = bBlogRepository.insertBlogLabelBatch(list);
            if(result!=list.size())
                throw new ServiceException(ResultEnum.UPDATE_ERROR);
        }



    }

    @Override
    public void deleteById(Integer id) {
        bBlogRepository.deleteBlogCategoryBatchByBlogId(id);
        bBlogRepository.deleteBlogLabelBatchByBlogId(id);
        int result = bBlogRepository.deleteByPrimaryKey(id);
        if(result!=1)
            throw new ServiceException(ResultEnum.DELETE_ERROR);
    }

    @Override
    public BlogResponseDTO findById(Integer id) {
        return bBlogRepository.selectBlogByQueryCondition(id);
    }
}
