package com.gwf.family.sys.user.service.impl;

import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.dto.ChangePasswordDto;
import com.gwf.family.sys.user.entity.SysUser;
import com.gwf.family.sys.user.service.SysUserService;
import com.gwf.family.sys.userroles.dao.SysUserRolesRepository;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by gwf on 2017-8-10 14:15:19.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;




    @Override
    public void changePassword(ChangePasswordDto dto) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getOldPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        int result = sysUserRepository.updatePasswordByUserName(dto.getUserName(),passwordEncoder.encode(dto.getNewpassword()));
        if(result==0)
            throw new ServiceException(ResultEnum.UPDATE_ERROR);
    }
}
