package com.tengzhi.base.jpa.dto;

import cn.hutool.core.util.NumberUtil;
import com.tengzhi.base.tools.json.MapperFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseDto {
    //额外参数
    private String params;
    //页码
    private int pageIndex;
    //单页数据数量
    private int pageSize;
    //排序字段
    private String sortField;
    //排序方式
    private String sortOrder;

    /**
     * 获取页码开始数量
     *
     * @return
     */
    public int getStart() {
        return pageIndex * pageSize;
    }
    public int getEnd() {
        return pageIndex * pageSize+pageSize;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String parms) {
        this.params = parms;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public BaseDto(){

    }
    public static BaseDto ValueOfRequest(HttpServletRequest request){
        return new BaseDto(){{
            setParams(request.getParameter("parms"));
            setPageIndex(NumberUtil.parseInt(request.getParameter("pageIndex")));
            setPageSize(NumberUtil.parseInt(request.getParameter("pageSize")));
            setSortField(request.getParameter("sortField"));
            setSortOrder(request.getParameter("sortOrder"));
        }};
    }
    /**
     * params字符串转JSON对象
     *
     * @return
     * @throws IOException
     */
    public Map<String, String> getParamsMap() {
        if (StringUtils.isEmpty(getParams())) {
            return new HashMap<String, String>(0);
        } else {
            try{
                return MapperFactory.getInstance().Str2Map(getParams());
            }catch(IOException e){
                //该处转抛出RuntimeException，因为传入的字符串不可控，为运行时异常
                throw new RuntimeException(e.getMessage(),e.getCause());
            }
        }
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "params='" + params + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", sortField='" + sortField + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
