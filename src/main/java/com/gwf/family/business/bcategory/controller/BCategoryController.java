package com.gwf.family.business.bcategory.controller;
import com.gwf.family.business.blabel.entity.BLabel;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.bcategory.entity.BCategory;
import com.gwf.family.business.bcategory.service.BCategoryService;
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
@RequestMapping("/b/category")
@Api(description = "分类CRUD")
public class BCategoryController {
    @Autowired
    private BCategoryService bCategoryService;

    @PostMapping
    @ApiOperation("添加分类")
    public Result add(BCategory bCategory) {
        bCategoryService.save(bCategory);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除分类")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bCategoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改分类")
    public Result update(BCategory bCategory) {
        bCategoryService.update(bCategory);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "根据分类id查询详情",response = BCategory.class)
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BCategory bCategory = bCategoryService.findById(id);
        return ResultGenerator.genSuccessResult(bCategory);
    }

    @GetMapping
    @ApiOperation(value = "分页查询分类列表",response = BCategory.class,responseContainer = "List")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BCategory> list = bCategoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
