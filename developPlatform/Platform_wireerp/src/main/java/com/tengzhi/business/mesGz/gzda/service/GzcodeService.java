package com.tengzhi.business.mesGz.gzda.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.mesGz.gzda.model.Gzda;
import com.tengzhi.business.mesGz.gzda.model.GzdaVo;

public interface GzcodeService {
	   BasePage<Gzda> findAll(BaseDto baseDto) throws IOException;

		
		List<Map> findGzmj(BaseDto baseDto) throws IOException;
		
		/*
		 * Result save(GzdaVo vo) throws IOException,Exception;
		 */
		
		/* BasePage<Jscpcode> findGzmj(BaseDto baseDto) throws IOException; */	
		
		void delete();
		
}
