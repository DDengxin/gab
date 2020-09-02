package com.tengzhi.base.jpa.page.impl;

import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BasePageImpl<T> extends PageImpl<T> implements BasePage<T> {

    private static final long serialVersionUID = 1L;
    private long pageTotal;

    @Override
    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public BasePageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        setPageTotal(total);
    }

    public BasePageImpl(List<T> content) {
        super(content);
    }

    public BasePageImpl(List<T> content, long total) {
        super(content);
        setPageTotal(total);
    }

    @Override
    public Result getResult() {
        return Result.formPage(this);
    }

    /**
     * 传入一个List对象，将它转化为分页对象(用于返回前端)
     *
     * @param list
     * @return
     */
    public static <V> BasePage<V> valueOfList(@NotNull List<V> list) {
        return new BasePageImpl(list, Pageable.unpaged(), list.size());
    }
}
