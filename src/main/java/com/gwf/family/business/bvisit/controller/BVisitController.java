package com.gwf.family.business.bvisit.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.bvisit.entity.BVisit;
import com.gwf.family.business.bvisit.service.BVisitService;
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
@RequestMapping("/b/visit")
public class BVisitController {
    @Autowired
    private BVisitService bVisitService;

    @PostMapping
    @ApiOperation("添加BVisit")
    public Result add(BVisit bVisit) {
        bVisitService.save(bVisit);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BVisit")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bVisitService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BVisit")
    public Result update(BVisit bVisit) {
        bVisitService.update(bVisit);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BVisit根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BVisit bVisit = bVisitService.findById(id);
        return ResultGenerator.genSuccessResult(bVisit);
    }

    @GetMapping
    @ApiOperation("BVisit分页查询列表")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BVisit> list = bVisitService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
