package com.gwf.family.business.bvisit.service.impl;

import com.gwf.family.business.buserinfo.dao.BUserInfoRepository;
import com.gwf.family.business.bvisit.dao.BVisitRepository;
import com.gwf.family.business.bvisit.entity.BVisit;
import com.gwf.family.business.bvisit.service.BVisitService;
import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by gwf on 2017-10-22 14:16:31.
 */
@Service
@Transactional
public class BVisitServiceImpl extends AbstractService<BVisit> implements BVisitService {
    @Autowired
    private BVisitRepository bVisitRepository;
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(BVisit model) {
        model.setVisitIp(getIpAddress(request));
        bVisitRepository.insertSelective(model);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

    @Override
    public List<BVisit> findByBlogId(Integer blogId) {
        return bVisitRepository.selectByBlogId(blogId);
    }
}
