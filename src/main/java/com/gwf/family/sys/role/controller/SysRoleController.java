package com.gwf.family.sys.role.controller;
import com.gwf.family.business.core.results.Result;
import com.gwf.family.business.core.results.ResultGenerator;
import com.gwf.family.sys.role.entity.SysRole;
import com.gwf.family.sys.role.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
* Created by gwf on 2017-8-10 14:15:19.
*/
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping
    @ApiIgnore
    public Result add(SysRole sysRole) {
        sysRoleService.save(sysRole);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiIgnore
    public Result delete(@PathVariable Integer id) {
        sysRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiIgnore
    public Result update(SysRole sysRole) {
        sysRoleService.update(sysRole);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiIgnore
    public Result detail(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysRole);
    }

    @GetMapping
    @ApiOperation("获取用户权限列表")
    public Result list(@RequestParam(name = "page",defaultValue = "1")@ApiParam("页数") Integer page,
                       @RequestParam(name = "size",defaultValue = "10")@ApiParam("每页行数") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRole> list = sysRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
