package com.gwf.family.business.bcomment.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.bcomment.entity.BComment;
import com.gwf.family.business.bcomment.service.BCommentService;
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
@RequestMapping("/b/comment")
public class BCommentController {
    @Autowired
    private BCommentService bCommentService;

    @PostMapping
    @ApiOperation("添加BComment")
    public Result add(BComment bComment) {
        bCommentService.save(bComment);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BComment")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bCommentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BComment")
    public Result update(BComment bComment) {
        bCommentService.update(bComment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BComment根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BComment bComment = bCommentService.findById(id);
        return ResultGenerator.genSuccessResult(bComment);
    }

    @GetMapping
    @ApiOperation("BComment分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BComment> list = bCommentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
