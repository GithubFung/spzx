package com.spzx.manager.service;

import com.spzx.commonutil.dto.system.LoginDto;
import com.spzx.commonutil.entity.system.SysUser;
import com.spzx.commonutil.vo.system.LoginVo;

/**
 * Created by 小冯 on 2023/11/5 0:19
 */
public interface SysUserService {

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);
}
