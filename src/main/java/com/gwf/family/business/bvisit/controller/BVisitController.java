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
@Api(description = "访客表",position = 10)
public class BVisitController {
    @Autowired
    private BVisitService bVisitService;

    @PostMapping
    @ApiOperation("添加BVisit")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BVisit bVisit) {
        bVisitService.save(bVisit);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping
    @ApiOperation(value = "分页查询访问量列表列表",response = BVisit.class,responseContainer = "List")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size,
                       @ApiParam(value = "博客id，不填写则查询全部")@RequestParam(name = "blogId",required = false) Integer blogId) {
        PageHelper.startPage(page, size);
        List<BVisit> list = bVisitService.findByBlogId(blogId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
