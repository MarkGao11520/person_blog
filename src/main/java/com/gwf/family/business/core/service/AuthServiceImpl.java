package com.gwf.family.business.core.service;

import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.common.util.JwtUtil;
import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.role.service.SysRoleService;
import com.gwf.family.sys.user.dao.SysUserRepository;
import com.gwf.family.sys.user.entity.SysUser;
import com.gwf.family.sys.userroles.dao.SysUserRolesRepository;
import com.gwf.family.sys.userroles.entity.SysUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private SysUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SysRoleService sysRoleService;
    private SysUserRolesRepository sysUserRolesRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           SysUserRepository userRepository,
                           SysUserRolesRepository sysUserRolesRepository,
                           SysRoleService sysRoleService,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.sysRoleService = sysRoleService;
        this.sysUserRolesRepository = sysUserRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    @Transactional
    public void register(SysUser userToAdd) {
        if(userRepository.findByUserName(userToAdd.getUsername())!=null)
            throw new ServiceException(ResultEnum.USER_EXISTS);
        StringBuilder ids = new StringBuilder();
        userToAdd.getRoles().stream().forEach(e -> ids.append(e.getId()).append(","));
        ids.replace(ids.lastIndexOf(","),ids.lastIndexOf(",")+1,"");
        List<SysRole> roleList = sysRoleService.findByIds(ids.toString());
        if(roleList.size()<1)
            throw new ServiceException(ResultEnum.ROLE_NOT_EXISTS);
        userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
        int result = userRepository.insertSelective(userToAdd);
        if(result==0)
            throw new ServiceException(ResultEnum.SAVE_ERROR);
        List<SysUserRoles> userRoles = userToAdd.getRoles().stream().map(e -> {
            SysUserRoles sysUserRoles =  new SysUserRoles();
            sysUserRoles.setRoleId(e.getId());
            sysUserRoles.setUserId(userToAdd.getId());
            return sysUserRoles;
        }).collect(Collectors.toList());
        result = sysUserRolesRepository.insertList(userRoles);
        if(result!=userRoles.size())
            throw new ServiceException(ResultEnum.SAVE_ERROR);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = JwtUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
