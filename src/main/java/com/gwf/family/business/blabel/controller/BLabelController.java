package com.gwf.family.business.blabel.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.blabel.entity.BLabel;
import com.gwf.family.business.blabel.service.BLabelService;
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
@RequestMapping("/b/label")
public class BLabelController {
    @Autowired
    private BLabelService bLabelService;

    @PostMapping
    @ApiOperation("添加BLabel")
    public Result add(BLabel bLabel) {
        bLabelService.save(bLabel);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BLabel")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bLabelService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BLabel")
    public Result update(BLabel bLabel) {
        bLabelService.update(bLabel);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BLabel根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BLabel bLabel = bLabelService.findById(id);
        return ResultGenerator.genSuccessResult(bLabel);
    }

    @GetMapping
    @ApiOperation("BLabel分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BLabel> list = bLabelService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
