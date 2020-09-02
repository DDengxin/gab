package com.tengzhi.business.cg.da.materialArchives_无处引用等待删除.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.js.product.model.Jscpcode;

@Deprecated
public interface MaterialArchivesService {
	
	BasePage<Jscpcode> findAll(BaseDto baseDto) throws IOException;

	Jscpcode findById(String Id);

	Jscpcode save(Jscpcode jscpcode) throws Exception;

	void update(Jscpcode jscpcode);

	void deleteById(String Id);
	
	List<Map> getTreeList(String mod,String type);
	
	List<Map> getCheckList(String mod,String type);

}
