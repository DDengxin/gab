package com.tengzhi.business.quality.qualityDetection.productDetection.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.constituent.model.EPzJyList;
import com.tengzhi.business.quality.qualityDetection.productDetection.model.EPzJylistJc;
import com.tengzhi.business.quality.qualityDetection.productDetection.model.EPzJylistJc.EPzJylistJc_PK;
import com.tengzhi.business.quality.qualityDetection.productDetection.service.vo.EPzJyListJcVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.params.model.SysParams;

public interface ProductDetectionService extends BaseService<EPzJylistJc,EPzJylistJc_PK> {

	/**
	 * 获取检测信息
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	Result getSrchTopList(BaseDto baseDto) throws IOException;
	
	
	/**
	 * 新增列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	Map<String, Object> getAddDetailed(BaseDto baseDto) throws IOException;
	
	/**
	 * 保存
	 * @param jcVo
	 * @return
	 * @throws Exception
	 */
	Result save(EPzJyListJcVo jcVo) throws Exception ;
	
	
	/**
	 * 修改
	 * @param jcVo
	 * @return
	 * @throws Exception
	 */
	Result update(EPzJyListJcVo jcVo) throws Exception ;
	
	/**
	 * 获取信息
	 * @param note
	 * @return
	 */
	EPzJyList getByNote(String note);
	
	/**
	 * 项目明细
	 * @param jyxm
	 * @return
	 */
	List<SysParams> paramsAll(String jyxm ) ;


	boolean judgeUnique(String pch, String dataMan);
	
	Result getInfoByPch(String pch,String pchType) ;
	
	/**
     * 通过批次号获取单号
     * @param pack
     */
    List<SelectVo> getNoteByPch(String pch,String pchType);
	
}
