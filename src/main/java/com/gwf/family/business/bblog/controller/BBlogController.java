package com.gwf.family.business.bblog.controller;
import com.gwf.family.business.bblog.dto.BlogRequestDTO;
import com.gwf.family.business.bblog.dto.BlogResponseDTO;
import com.gwf.family.business.bblog.dto.QueryConditionDTO;
import com.gwf.family.business.bcategory.service.BCategoryService;
import com.gwf.family.business.blabel.service.BLabelService;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.bblog.entity.BBlog;
import com.gwf.family.business.bblog.service.BBlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwf.family.common.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Created by gwf on 2017-10-22 14:16:31.
*/
@RestController
@RequestMapping("/b/blog")
@Api(description = "博客CRUD",position = 7)
public class BBlogController {
    @Autowired
    private BBlogService bBlogService;
    @Autowired
    private BCategoryService bCategoryService;
    @Autowired
    private BLabelService bLabelService;

    @Value("${blog.coverurl.location}")
    private String coverUrlLocation;

    @PostMapping
    @ApiOperation("添加博客")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BlogRequestDTO dto) {
        bBlogService.saveDto(dto);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/upload/coverurl")
    @ApiOperation("上传封面")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result uploadHeadPic(MultipartFile headPic){
        String url = UploadUtil.picImport(coverUrlLocation,headPic);
        return ResultGenerator.genSuccessResult(url);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("根据id删除博客")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bBlogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("根据id修改博客信息")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(@ApiParam(value = "id") @PathVariable  Integer id,BlogRequestDTO dto) {
        bBlogService.updateDto(id,dto);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "根据id查询博客详情",response = BlogResponseDTO.class)
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BlogResponseDTO bBlog = bBlogService.findById(id);
        return ResultGenerator.genSuccessResult(bBlog);
    }

    @GetMapping
    @ApiOperation(value = "根据条件分页查询博客列表",response = BlogResponseDTO.class,responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size,
                       QueryConditionDTO dto) {
        PageHelper.startPage(page, size);
        List<BlogResponseDTO> list = bBlogService.findListByQueryConditionDto(dto);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
