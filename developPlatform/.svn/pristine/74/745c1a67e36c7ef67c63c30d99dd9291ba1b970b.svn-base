package com.tengzhi.app.menu.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.right.model.SysRight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuDao extends BasedaoRepository<SysRight, String> {

    @Query(value = "  from SysRight where rightModule= :rightModule and  parentId = :parentId order by rightOrd ASC")
    List<SysRight>
    findAppBottomMenu(@Param("rightModule") String rightModule,@Param("parentId") String parentId);
}
