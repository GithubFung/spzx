package com.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.spzx.commonutil.vo.system.ValidateCodeVo;
import com.spzx.manager.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 小冯 on 2023/11/7 23:21
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 生成图片验证码
     *
     * @return
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        //1、通过hutool工具生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        //4位验证码的值
        String code = circleCaptcha.getCode();
        //验证码图片，使用Base64编码
        String imageBase64 = circleCaptcha.getImageBase64();

        //2、将验证码存入redis，设置redis的key:uuid  value:验证码的值
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:validate" + key, code, 5, TimeUnit.MINUTES);

        //3、返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        //redis存储的key
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        return validateCodeVo;
    }
}
