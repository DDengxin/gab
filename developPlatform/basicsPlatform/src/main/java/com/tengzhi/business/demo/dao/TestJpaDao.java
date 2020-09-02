package com.tengzhi.business.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.demo.model.Test;

public interface TestJpaDao extends BasedaoRepository<Test, String> {
	@Modifying
	@Query("delete from Test  where userId = :userId")
    void deleteByUserId(@Param("userId") String userId);
}
