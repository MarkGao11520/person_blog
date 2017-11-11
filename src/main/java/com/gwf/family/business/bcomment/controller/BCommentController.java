package com.gwf.family.business.bcomment.controller;
import com.gwf.family.business.bcomment.dto.CommentDTO;
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
@Api(description = "评论CRUD",position = 8)
public class BCommentController {
    @Autowired
    private BCommentService bCommentService;

    @PostMapping
    @ApiOperation("添加博客")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BComment bComment) {
        bCommentService.save(bComment);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("根据id删除博客")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bCommentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

//    @PutMapping("/{id:\\d+}")
//    @ApiOperation("修改BComment")
//    @ApiResponses({
//            @ApiResponse(code = 401,message = "权限不足"),
//            @ApiResponse(code = 403,message = "不合法的token验证"),
//            @ApiResponse(code = 500,message = "服务器内部错误"),
//            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
//    public Result update(BComment bComment) {
//        bCommentService.update(bComment);
//        return ResultGenerator.genSuccessResult();
//    }

//    @GetMapping("/{id:\\d+}")
//    @ApiOperation(value = "BComment根据id查询详情",response = CommentDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 401,message = "权限不足"),
//            @ApiResponse(code = 403,message = "不合法的token验证"),
//            @ApiResponse(code = 500,message = "服务器内部错误"),
//            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
//    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
//        BComment bComment = bCommentService.findById(id);
//        return ResultGenerator.genSuccessResult(bComment);
//    }

    @GetMapping
    @ApiOperation(value = "根据博客id分页查询列表",response = CommentDTO.class,responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size,
                       @ApiParam(value = "博客id")@RequestParam(name = "blogId")Integer blogId) {
        PageHelper.startPage(page, size);
        List<CommentDTO> list = bCommentService.findByBlogId(blogId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
