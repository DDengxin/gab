package com.tengzhi.business.system.workflow.excpetion;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.result.Result;

/**
 * 工作流异常类
 *
 * @author: gaodu
 * @date: 2020/7/7 9:35
 **/
public class WorkFlowException extends RuntimeException {
    /**
     * 状态码x
     */
    private int code = 500;

    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public WorkFlowException(String message) {
        super(message);
    }

    public WorkFlowException(String message, int code) {
        super(message);
        setCode(code);
    }


    /**
     * 流程模板不存在
     *
     * @return
     */
    public static WorkFlowException ProcessNotExist(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "流程模板不存在"), 404);
    }

    /**
     * 流程实例不存在
     *
     * @return
     */
    public static WorkFlowException InstanceNotExist(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "流程实例不存在"), 404);
    }

    /**
     * 请求参数错误
     * @return
     */
    public static WorkFlowException RequestParameterError(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "请求参数错误"), 400);
    }



    /**
     * 流程配置错误
     *
     * @param message
     * @return
     */
    public static WorkFlowException ProcessConfigurationError(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "流程配置错误"), 405);
    }

    /**
     * 流程操作错误
     *
     * @param message
     */
    public static WorkFlowException ProcesOperationError(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "流程操作错误"), 403);
    }

    /**
     * 流程运行错误
     *
     * @param message
     * @return
     */
    public static WorkFlowException ProcessRunError(String message) {
        return new WorkFlowException(StrUtil.nullToDefault(message, "流程运行错误"), 500);
    }

}
