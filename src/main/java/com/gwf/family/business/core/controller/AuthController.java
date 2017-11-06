package com.gwf.family.business.core.controller;

import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.core.service.AuthService;
import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.user.entity.SysUser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@RestController
@Api(description = "权限校验",position = 0)
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "用户名密码错误"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result createAuthenticationToken(@RequestParam("username") @ApiParam("用户名") String username,
                                            @RequestParam("password") @ApiParam("密码") String password,
                                            @RequestParam("type") @ApiParam("登录类型，1为管理员登录，2为普通用户登录") Integer type){
        final String token = authService.login(username, password,type);
        return ResultGenerator.genSuccessResult(token);
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    @ApiOperation("刷新token")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genSuccessResult(refreshedToken);
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    @ApiOperation("注册用户")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result register(SysUser addedUser){
        if(addedUser.getRoles()==null||addedUser.getRoles().size()==0){
            List<SysRole> sysRoles = new ArrayList<>();
            sysRoles.add(new SysRole(2,"ROLE_USER"));
            addedUser.setRoles(sysRoles);
        }
        authService.register(addedUser);
        return ResultGenerator.genSuccessResult();
    }
}

