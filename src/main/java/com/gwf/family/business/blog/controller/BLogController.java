package com.gwf.family.business.blog.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.blog.entity.BLog;
import com.gwf.family.business.blog.service.BLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

/**
* Created by gwf on 2017-11-5 18:39:48.
*/
@RestController
@RequestMapping("/b/log")
public class BLogController {
    @Autowired
    private BLogService bLogService;

    @PostMapping
    @ApiOperation("添加BLog")
    public Result add(BLog bLog) {
        bLogService.save(bLog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BLog")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BLog")
    public Result update(BLog bLog) {
        bLogService.update(bLog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BLog根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BLog bLog = bLogService.findById(id);
        return ResultGenerator.genSuccessResult(bLog);
    }

    @GetMapping
    @ApiOperation("BLog分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BLog> list = bLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
