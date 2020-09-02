package com.tengzhi.business.dynamicrow.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.dynamicrow.model.dynamicrow;

import java.util.List;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 */
public interface rowDao extends BasedaoRepository<dynamicrow,String> {

    int deleteByRowIdStartingWith(String menuId);

    int deleteByRowIdNotInAndRowIdStartingWith(List<String> RowIds, String MenuId);

}
