package com.tengzhi.IM.business.upload.service;

import com.tengzhi.IM.business.upload.model.imFile;
import com.tengzhi.base.jpa.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ImUploadService extends BaseService<imFile, String>{


	Map<String,Object> uploadFile(MultipartFile file);

	Map<String,Object> download(String uuid ,HttpServletRequest request, HttpServletResponse response);

}
