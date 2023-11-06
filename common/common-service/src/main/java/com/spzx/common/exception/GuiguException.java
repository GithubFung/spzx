package com.spzx.common.exception;

import com.spzx.commonutil.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * Created by 小冯 on 2023/11/7 0:34
 */
@Data
public class GuiguException extends RuntimeException {

    private Integer code;

    private String message;

    private ResultCodeEnum resultCodeEnum;

    public GuiguException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }
}
