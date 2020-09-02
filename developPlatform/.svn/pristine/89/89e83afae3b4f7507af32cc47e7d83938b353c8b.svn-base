package com.tengzhi.base.jpa.page;

import com.tengzhi.base.jpa.result.Result;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface BasePage<T> extends Page<T> {
    /**
     * 直接返回Result对象
     *
     * @return
     */
    Result getResult();

    /**
     * 获取当前分页对象总页码数量
     * @return
     */
    long getPageTotal();
}
