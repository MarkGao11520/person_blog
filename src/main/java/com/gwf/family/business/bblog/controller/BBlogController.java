package com.gwf.family.business.bblog.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.bblog.entity.BBlog;
import com.gwf.family.business.bblog.service.BBlogService;
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
@RequestMapping("/b/blog")
public class BBlogController {
    @Autowired
    private BBlogService bBlogService;

    @PostMapping
    @ApiOperation("添加BBlog")
    public Result add(BBlog bBlog) {
        bBlogService.save(bBlog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BBlog")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bBlogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BBlog")
    public Result update(BBlog bBlog) {
        bBlogService.update(bBlog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BBlog根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BBlog bBlog = bBlogService.findById(id);
        return ResultGenerator.genSuccessResult(bBlog);
    }

    @GetMapping
    @ApiOperation("BBlog分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BBlog> list = bBlogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
