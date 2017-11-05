package com.gwf.family.business.buserinfo.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import com.gwf.family.business.buserinfo.service.BUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

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
    public Result add(BUserInfo bUserInfo) {
        bUserInfoService.save(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BUserInfo")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bUserInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BUserInfo")
    public Result update(BUserInfo bUserInfo) {
        bUserInfoService.update(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BUserInfo根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BUserInfo bUserInfo = bUserInfoService.findById(id);
        return ResultGenerator.genSuccessResult(bUserInfo);
    }

    @GetMapping
    @ApiOperation("BUserInfo分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BUserInfo> list = bUserInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
