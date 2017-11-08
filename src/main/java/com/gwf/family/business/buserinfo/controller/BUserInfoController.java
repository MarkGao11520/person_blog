package com.gwf.family.business.buserinfo.controller;
import com.gwf.family.business.buserinfo.dto.UserQueryConditionDto;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultEnum;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.business.buserinfo.entity.BUserInfo;
import com.gwf.family.business.buserinfo.service.BUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwf.family.common.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
* Created by gwf on 2017-10-22 14:16:31.
*/
@RestController
@RequestMapping("/b/user/info")
@Api(description = "用户操作",position = 4)
public class BUserInfoController {
    @Autowired
    private BUserInfoService bUserInfoService;

    @Value("${blog.headPic.location}")
    private String headPicLocation;

    @PostMapping
    @ApiOperation("添加BUserInfo")
    @ApiIgnore
    public Result add(BUserInfo bUserInfo) {
        bUserInfoService.save(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除BUserInfo")
    @ApiIgnore
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        bUserInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改BUserInfo")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    @PostAuthorize("returnObject.data.username == principal.username or hasRole('ROLE_ADMIN')")
    public Result update(BUserInfo bUserInfo,@PathVariable  Integer id) {
        bUserInfo.setId(id);
        bUserInfoService.update(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }


    @PutMapping("/lock/{id:\\d+}")
    @ApiOperation(value = "锁定用户")
//    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result update(@PathVariable  Integer id,@RequestParam("isLock")@ApiParam("1加锁0解锁") Integer isLock) {
        BUserInfo bUserInfo = new BUserInfo();
        bUserInfo.setId(id);
        if(isLock!=0&&isLock!=1)
            throw new ServiceException(ResultEnum.PARAM_ERROR);
        bUserInfo.setIsLock(isLock);
        bUserInfoService.update(bUserInfo);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "BUserInfo根据id查询详情",response = BUserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
    public Result detail(@ApiParam(value = "用户id")@PathVariable Integer id) {
        BUserInfo bUserInfo = bUserInfoService.findById(id);
        return ResultGenerator.genSuccessResult(bUserInfo);
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

    @GetMapping
    @ApiOperation(value = "根据条件分页查询用户列表",response = BUserInfo.class,responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 401,message = "权限不足"),
            @ApiResponse(code = 403,message = "不合法的token验证"),
            @ApiResponse(code = 500,message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "业务逻辑错误的具体原因")})
//    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public Result list(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                       @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size,
                       UserQueryConditionDto userQueryConditionDto) {
        PageHelper.startPage(page, size);
        List<BUserInfo> list = bUserInfoService.findByUserQueryCondition(userQueryConditionDto);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
