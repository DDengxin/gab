package com.tengzhi.business.production.productionSite.productPacking.service;

import java.io.IOException;
import java.util.List;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.production.productionSite.productPacking.vo.ProductPackingVo;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;

public interface ProductPackingService extends BaseService {
	
	/**
	 * 工程号
	 * @param baseDto
	 * @return
	 * @throws Exception
	 */
	BasePage<MScGclist> getSrchTopList(BaseDto baseDto) throws Exception;
	
	/**
	 * 工程明细
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<MScGclist> getSrchBottomList(BaseDto baseDto) throws IOException;
	
	/**
	 * 
	 * @param gch
	 * @return
	 */
	MScGclist findByNote(String gch);
	
	/**
	 * 入库确认
	 * @param gch
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	Result putIn(String gch) throws IOException,Exception;
	
	
	/**
	 * 获取预包清单
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<MScGclist> getPrePackList(BaseDto baseDto) throws IOException;
	/**
	 * 获取包装清单
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<MScGclist> getPackList(BaseDto baseDto) throws IOException;
	
	/**
	 * 判断多个工程的材质规格等是否一致
	 * @param inMode 	工程号、料架号
	 * @param scanData  扫描数据
	 * @return
	 * @throws IOException
	 */
	Result getConsistent (String inMode,String scanDatas) throws IOException;
	
	/**
	 * 包装
	 * @param productPackingVo
	 * @return
	 * @throws IOException
	 */
	Result addPacks(ProductPackingVo productPackingVo)throws IOException;
	
	/**
	 * 删除包装
	 * @param productPackingVo
	 * @return
	 * @throws IOException
	 */
	Result deletePacks(ProductPackingVo productPackingVo) throws IOException;
	
	/**
	 * 删除单号
	 * @param note
	 * @return
	 * @throws IOException
	 */
	Result deleteAllPacks(String note) throws IOException;
	/**
	 * 打印（插入数据到临时表）
	 * @param printType 打印数据来源（工程表）
	 * @param packType 打印类型
	 * @param inputValue 打印数据
	 * @return
	 * @throws IOException
	 */
	Result printPack(String printType,String packType ,String inputValue)throws IOException;
}
