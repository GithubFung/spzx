package com.spzx.manager.controller;

import com.spzx.manager.service.SysUserService;
import com.spzx.commonutil.dto.system.LoginDto;
import com.spzx.commonutil.vo.common.Result;
import com.spzx.commonutil.vo.common.ResultCodeEnum;
import com.spzx.commonutil.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 小冯 on 2023/11/5 0:17
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
