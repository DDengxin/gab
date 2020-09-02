package com.tengzhi.business.system.library.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.library.model.Library;

public interface LibraryDao extends BasedaoRepository<Library, String> {
	@Query(value = " from Library where arUuid = :arUuid")
	public Library findByArUuid(@Param(value="arUuid")String arUuid);
	
	@Modifying
	@Query(value = "update Library set arNewest = :arNewest where arUuid = :arUuid")
	public void updateNewEst(@Param(value="arNewest")boolean arNewest,@Param(value="arUuid")String arUuid);
}
