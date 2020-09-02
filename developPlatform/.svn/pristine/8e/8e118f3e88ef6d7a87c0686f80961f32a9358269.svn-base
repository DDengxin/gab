package com.tengzhi.business.resouces.vo;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tengzhi.base.tools.json.MapperFactory;

public class ResultVo {
    // 状态
    private boolean status;
    // 信息
    private String message;
    // 操作码
    private String flag;
    // 数据
    private Object data;
    // 详情
    private Object detail;
    // 总数
    private Object total;
    //
    private Object summary;
    // 数据源
    private Object source;

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getSummary() {
        return summary;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    /************构造函数***********/
    public ResultVo() {
    }

    public ResultVo(boolean status, String message, Object data) {
        set(status, message, data);
    }

    public ResultVo(boolean status, String message) {
        set(status, message);
    }
    /************END 构造函数***********/


    /************构造函数***********/
    /**
     * 绑定值
     *
     * @param status
     * @param message
     * @param data
     * @param flag
     */
    public void set(Boolean status, String message, Object data, Object detail, String flag) {
        setStatus(status);
        setMessage(message);
        setData(data);
        setDetail(detail);
        setFlag(flag);
    }

    public void set(Boolean status, String message, Object data, Object detail) {
        set(status, message, data, detail);
    }

    public void set(Boolean status, String message, Object data) {
        set(status, message, data, null, null);
    }

    public void set(Boolean status, String message) {
        set(status, message, null, null, null);
    }

    public void set(Boolean status) {
        set(status, null, null, null, null);
    }
    /************END 构造函数***********/


    /************返回错误***********/
    public static ResultVo error(String message) {
        return new ResultVo(false, message);
    }

    public static ResultVo error(Exception e) {
        return new ResultVo(false, e.getMessage());
    }
    /************END 返回错误***********/

    /************返回成功***********/
    public static ResultVo success(Object data, String message) {
        return new ResultVo(true, message, data);
    }

    public static ResultVo success(Object data) {
        return ResultVo.success(data, null);
    }

    public static ResultVo success() {
        return ResultVo.success(null, null);
    }
    /************END 返回成功***********/


    /**
     * 返回Map对象
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap() {
        return MapperFactory.getObjectMapper().convertValue(this, Map.class);
    }


    /*
     * 清除对象绑定的值
     */
    public void clear() {
        this.status = false;
        this.message = null;
        this.data = null;
        this.flag = null;
        this.total = null;
        this.summary = null;
        this.source = null;
        this.detail = null;
    }

    @Override
    public String toString() {
        try {
            return MapperFactory.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    }
}
