package com.spzx.manager.mapper;

import com.spzx.commonutil.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 小冯 on 2023/11/5 0:21
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询数据库用户信息
     * @param userName
     * @return
     */
    SysUser selectUserInfoByUserName(String userName);
}
