package com.gwf.family.business.bblog.service;
import com.gwf.family.business.bblog.dto.BlogRequestDTO;
import com.gwf.family.business.bblog.dto.BlogResponseDTO;
import com.gwf.family.business.bblog.dto.QueryConditionDTO;
import com.gwf.family.business.bblog.entity.BBlog;
import com.gwf.family.business.core.service.Service;

import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
public interface BBlogService extends Service<BBlog> {

    void saveDto(BlogRequestDTO dto);

    List<BlogResponseDTO> findListByQueryConditionDto(QueryConditionDTO dto);
}
