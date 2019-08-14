package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 定义异常抛出类
 */
public class ExceptionCast {

    //使用此方法抛出异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
