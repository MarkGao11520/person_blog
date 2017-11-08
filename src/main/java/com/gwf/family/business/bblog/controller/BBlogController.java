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

    @Value("${blog.headpic.location}")
    private String headPicLocation;

    @PostMapping
    @ApiOperation("添加博客")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result add(BlogRequestDTO dto) {
        if(dto.getCategoryId()==null||bCategoryService.findById(dto.getCategoryId())==null)
            throw new ServiceException(ResultEnum.PARAM_ERROR);
        if(StringUtils.isEmpty(dto.getLabelIds())||bLabelService.findByIds(dto.getLabelIds())==null)
            throw new ServiceException(ResultEnum.PARAM_ERROR);
        bBlogService.saveDto(dto);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/upload/headpic")
    @ApiOperation("上传图片")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result uploadHeadPic(MultipartFile headPic){
        String url = UploadUtil.picImport(headPicLocation,headPic);
        return ResultGenerator.genSuccessResult(url);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BBlog")
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
    @ApiOperation("修改BBlog")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(BBlog bBlog) {
        bBlogService.update(bBlog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("BBlog根据id查询详情")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BBlog bBlog = bBlogService.findById(id);
        return ResultGenerator.genSuccessResult(bBlog);
    }

    @GetMapping
    @ApiOperation(value = "根据条件分页查询博客列表",response = BlogResponseDTO.class)
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
