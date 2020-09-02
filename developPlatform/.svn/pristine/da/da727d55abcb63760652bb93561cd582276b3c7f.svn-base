package com.tengzhi.business.finance.complaint.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.complaint.model.Complaint;
import com.tengzhi.business.finance.complaint.model.ComplaintMx;

public interface ComplaintService extends BaseService<Complaint,String>{

	BasePage<Map<String, Object>> search(BaseDto baseDto) throws IOException;

	BasePage<Map<String, Object>> getHtCode(BaseDto dto) throws IOException;

	BasePage<Map<String, Object>> getProductSelectionList(BaseDto dto) throws IOException;

	List<Map> getCpcode(String ht_code);

	Result add(Complaint complaint) throws Exception;

	Map<String, Object> getOne(String ksNote);

	String getDw(String ksDw);

	Result update(Complaint complaint) throws Exception;

	Result accept(String ksNote);

	Result acceptance(Complaint complaint);

	String getMan(String slMan);

	Result dispose(ComplaintMx complaintMx) throws Exception;

	Result disposeFlag(String ksNote) throws Exception;

	Result closeout(Complaint complaint);
	
	List<Map> gridSearch(String ksNote);

	Result deleteMx(String ksNote);
	
	Result deleteAll(String ksNote);
	
}
