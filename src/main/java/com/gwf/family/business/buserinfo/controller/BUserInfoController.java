package com.gwf.family.business.buserinfo.controller;
import com.gwf.family.business.buserinfo.dto.UserQueryConditionDto;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import com.gwf.family.business.buserinfo.service.BUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
* Created by gwf on 2017-10-22 14:16:31.
*/
@RestController
@RequestMapping("/b/user/info")
public class BUserInfoController {
    @Autowired
    private BUserInfoService bUserInfoService;

    @PostMapping
    @ApiOperation("添加BUserInfo")
    @ApiIgnore
    public Result add(BUserInfo bUserInfo) {
        bUserInfoService.save(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BUserInfo")
    @ApiIgnore
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bUserInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BUserInfo")
    @PostAuthorize("returnObject.data.username == principal.username or hasRole('ROLE_ADMIN')")
    public Result update(BUserInfo bUserInfo,@PathVariable  Integer id) {
        bUserInfo.setId(id);
        bUserInfoService.update(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }


    @PutMapping("/lock/{id:\\d+}")
    @ApiOperation("锁定用户")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public Result update(@PathVariable  Integer id,@RequestParam("isLock")@ApiParam("1加锁0解锁") Integer isLock) {
        BUserInfo bUserInfo = new BUserInfo();
        bUserInfo.setId(id);
        if(isLock!=0&&isLock!=1)
            throw new ServiceException(ResultEnum.PARAM_ERROR);
        bUserInfo.setIsLock(isLock);
        bUserInfoService.update(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id:\\d+}")
    @ApiOperation("BUserInfo根据id查询详情")
    public Result detail(@ApiParam(value = "用户id")@PathVariable Integer id) {
        BUserInfo bUserInfo = bUserInfoService.findById(id);
        return ResultGenerator.genSuccessResult(bUserInfo);
    }

    @GetMapping
    @ApiOperation("根据条件分页查询用户列表")
//    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size,
                       UserQueryConditionDto userQueryConditionDto) {
        PageHelper.startPage(page, size);
        List<BUserInfo> list = bUserInfoService.findByUserQueryCondition(userQueryConditionDto);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
