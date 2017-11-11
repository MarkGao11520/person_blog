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
@Api(description = "点赞CURD",position = 8)
public class BRBlogFabulousController {
    @Autowired
    private BRBlogFabulousService bRBlogFabulousService;

    @PostMapping
    @ApiOperation("点赞")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BRBlogFabulous bRBlogFabulous) {
        bRBlogFabulousService.save(bRBlogFabulous);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping()
    @ApiOperation("根据id取消点赞")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(BRBlogFabulous brBlogFabulous) {
        bRBlogFabulousService.delete(brBlogFabulous);
        return ResultGenerator.genSuccessResult();
    }
}
