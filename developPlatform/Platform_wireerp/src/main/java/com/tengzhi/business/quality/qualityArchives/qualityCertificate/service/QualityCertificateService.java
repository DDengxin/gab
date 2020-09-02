package com.tengzhi.business.quality.qualityArchives.qualityCertificate.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.quality.qualityArchives.qualityCertificate.model.QualityCertificate;
import com.tengzhi.business.system.params.model.SysParams;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface QualityCertificateService extends BaseService {

	/**
	 * 获取质量证明信息
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<QualityCertificate> getSrchTopList(BaseDto baseDto) throws IOException;
	
	
	
	
	Result getSrchBottomList(BaseDto baseDto) throws IOException;


	Result getSrchBottomList0000(BaseDto baseDto) throws IOException;

	Result getSrchBottomList1111(BaseDto baseDto) throws IOException;


	/**
	 * 获取获取批号列表
	 * @param baseDto
	 * @return
	 * @throws IOException
	 */
	BasePage<QualityCertificate> getLotNoList(BaseDto baseDto) throws IOException;
	
	
	Map<Object,String> getAddQualityCertificate(String lotNo);
	
	
	Result saveData(Map<String,Object> map) throws Exception;
	
	Result updateData(Map<String,Object> map) throws IOException;
	
	
	Result deleteByNote(String note) ;
	
	List<SysParams> paramsAll(String pch);
	
	QualityCertificate getByNote(String note);
	
	
	 Map<String, Object> getzmNoteAlone(String note);

	
	
	BasePage<ECkOut> getOutList(BaseDto baseDto) throws IOException;
	
	Result getFlag(String note,String flag) ;
	
	ModelAndView table(ModelAndView mv, String note);

	Result confirm(String outNote);

	Result cancel(String outNote);
}
