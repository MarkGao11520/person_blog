package com.gwf.family.business.bvisit.service;
import com.gwf.family.business.bvisit.entity.BVisit;
import com.gwf.family.business.core.service.Service;

import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
public interface BVisitService extends Service<BVisit> {

    List<BVisit> findByBlogId(Integer blogId);
}
