package com.tengzhi.base.tools.expandcollection.impl;

import cn.hutool.core.util.ArrayUtil;
import com.tengzhi.base.tools.expandcollection.PredicateList;

import java.util.ArrayList;

/**
 * @author 鱼游浅水
 * 扩展ArrayList|后续可自己扩展
 * @create 2020-08-16
 */
public class PredicateArray<E> extends ArrayList<E> implements PredicateList<E> {

    @Override
    public void addNotEmpty(E e,Object ... obj) {
        if (ArrayUtil.isAllNotEmpty(obj)) {
            super.add(e);
        }
    }

}
