package com.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.spzx.common.exception.GuiguException;
import com.spzx.commonutil.dto.system.LoginDto;
import com.spzx.commonutil.entity.system.SysUser;
import com.spzx.commonutil.vo.common.ResultCodeEnum;
import com.spzx.commonutil.vo.system.LoginVo;
import com.spzx.manager.mapper.SysUserMapper;
import com.spzx.manager.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 小冯 on 2023/11/5 0:20
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录
     *
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        //1、获取提交的用户名
        String username = loginDto.getUserName();

        //2、根据用户名查询数据库sys_user表
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(username);

        //3、如果根据用户名查不到对应信息，用户不存在，返回错误信息
        if (sysUser == null) {
            /*throw new RuntimeException("用户名不存在");*/
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //4、如果根据用户名查询到用户名，用户存在
        //5、获取输入的密码，比较输入的密码和数据库密码是否一致
        String database_password = sysUser.getPassword();
        //数据库中的密码已加密，所以先把输入的密码进行加密，再进行比较
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());

        //6、如果密码一致，登录成功，否则登录失败
        if (database_password != input_password) {
            /*throw new RuntimeException("密码不正确");*/
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //7、登录成功，生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        //8、把登录成功用户信息放到redis里面
        redisTemplate.opsForValue()
                .set("user:login" + token,
                        JSON.toJSONString(sysUser),
                        7,
                        TimeUnit.DAYS);
        //9、返回loginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }
}
