package com.tengzhi.business.platform.need.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.platform.need.model.G_UserCollection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserCollectDao  extends BasedaoRepository<G_UserCollection,String>{
	
	@Modifying
	@Query(value =" delete from g_user_collection where collect_url = :collect_url  and user_id =:user_id ",nativeQuery = true)
	int  deleteByNote(@Param("collect_url") String collect_note ,@Param("user_id") String user_id );
	

}
