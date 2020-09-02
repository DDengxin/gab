package com.tengzhi.business.finance.voucher.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.cg.da.sysCustomer.model.SysCustomer;
import com.tengzhi.business.finance.voucher.model.CertificateWord;
public interface CertificateWordService extends BaseService{


	
	ModelAndView table(ModelAndView mv,String outNote);

	
	BasePage<CertificateWord> getSrchTopList(BaseDto baseDto) throws IOException;
	CertificateWord save(CertificateWord certificateWord) throws Exception;
	
	void update(CertificateWord certificateWord) throws Exception;
	void deleteByFgenerateid(Long fgenerateid);
    boolean judgeUnique(CertificateWord CertificateWord);
}
