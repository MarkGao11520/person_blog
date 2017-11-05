package com.gwf.family.business.brblogfabulous.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.brblogfabulous.entity.BRBlogFabulous;
import com.gwf.family.business.brblogfabulous.service.BRBlogFabulousService;
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
@RequestMapping("/b/r/blog/fabulous")
public class BRBlogFabulousController {
    @Autowired
    private BRBlogFabulousService bRBlogFabulousService;

    @PostMapping
    @ApiOperation("添加BRBlogFabulous")
    public Result add(BRBlogFabulous bRBlogFabulous) {
        bRBlogFabulousService.save(bRBlogFabulous);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BRBlogFabulous")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bRBlogFabulousService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BRBlogFabulous")
    public Result update(BRBlogFabulous bRBlogFabulous) {
        bRBlogFabulousService.update(bRBlogFabulous);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BRBlogFabulous根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BRBlogFabulous bRBlogFabulous = bRBlogFabulousService.findById(id);
        return ResultGenerator.genSuccessResult(bRBlogFabulous);
    }

    @GetMapping
    @ApiOperation("BRBlogFabulous分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BRBlogFabulous> list = bRBlogFabulousService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
