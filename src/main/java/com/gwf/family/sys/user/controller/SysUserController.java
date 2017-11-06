package com.gwf.family.sys.user.controller;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.sys.user.dto.ChangePasswordDto;
import com.gwf.family.sys.user.entity.SysUser;
import com.gwf.family.sys.user.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
* Created by gwf on 2017-8-10 14:15:19.
*/
@RestController
@RequestMapping("/sys/user")
@Api(description = "系统用户（登录账号）相关",position = 1)
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

//    @PostMapping
    public Result add(SysUser sysUser) {
        sysUserService.save(sysUser);
        return ResultGenerator.genSuccessResult();
    }
//    @DeleteMapping("/{id:\\d+}")
    public Result delete(@PathVariable Integer id) {
        sysUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

  //  @PutMapping("/{id:\\d+}")
    public Result update(SysUser sysUser) {
        sysUserService.update(sysUser);
        return ResultGenerator.genSuccessResult();
    }

 //   @PostAuthorize("returnObject.data.username == principal.username or hasRole('ROLE_ADMIN')")
 //   @GetMapping("/{id:\\d+}")
    public Result detail(@PathVariable Integer id) {
        SysUser sysUser = sysUserService.findById(id);
        if(sysUser==null)
            throw new ServiceException(ResultEnum.USER_NOT_EXISTS);
        return ResultGenerator.genSuccessResult(sysUser);
    }

 //   @GetMapping
    public Result list(@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PutMapping("/changepassword")
    @ApiOperation("修改密码")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result changePassword(ChangePasswordDto dto){
        sysUserService.changePassword(dto);
        return ResultGenerator.genSuccessResult();
    }
}
