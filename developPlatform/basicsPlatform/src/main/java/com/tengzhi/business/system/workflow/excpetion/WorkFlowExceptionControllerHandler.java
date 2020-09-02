package com.tengzhi.business.system.workflow.excpetion;

import com.tengzhi.base.jpa.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: gaodu
 * @date: 2020/7/7 11:31
 **/
@RestControllerAdvice(basePackages = "com.tengzhi.business.system.workflow.controller")
public class WorkFlowExceptionControllerHandler {
    /**
     * 配置统一异常处理
     * 该异常处理用于统一捕获WorkFlowException异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(WorkFlowException.class)
    public Result exceptionHandler(WorkFlowException e) {
        return Result.resultError(e.getCode(), e.getMessage());
    }

}
