package com.tengzhi.business.js.cpbom.service;

import java.io.IOException;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.business.js.cpbom.model.EJsCpcode;

public interface CpBomService {

	BasePage<Map<String, Object>> sreach(BaseDto baseDto) throws IOException;

	void save(EJsCpcode eJsCpcode) throws Exception;

	EJsCpcode findById(String cpcodeId);

	Map<String, Object> getCpcode(String cpcodeId);

	void update(EJsCpcode eJsCpcode);

	BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

	String getBom() throws Exception;

	void delete(String cpcodeId, String cpcodeUid, String cpcodeBom);

	void deleteByBom(String cpcodeBom, String cpcodeStype, String cpcodeId);

}
