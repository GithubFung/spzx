package com.spzx.manager.service;

import com.spzx.commonutil.vo.system.ValidateCodeVo;

/**
 * Created by 小冯 on 2023/11/7 23:21
 */
public interface ValidateCodeService {

    /**
     * 生成图片验证码
     *
     * @return
     */
    ValidateCodeVo generateValidateCode();
}
