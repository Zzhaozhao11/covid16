package com.tust.comment;


import com.tust.exception.BusinessException;
import com.tust.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result deException(Exception ex){
        //记录日志
        //发送消息给运维
        //发送电子邮箱给开发人员
        return Result.fail(1500 ,null,"系统繁忙，请稍后再试");
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送电子邮箱给开发人员
        return Result.fail(ex.getCode(),null,ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        return Result.fail(ex.getCode(),null,ex.getMessage());
    }
}