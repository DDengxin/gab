package com.tengzhi.IM.business.message.service;

import com.tengzhi.IM.business.message.model.SysImMessage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface SysImMessageService extends BaseService {

     Result updateTimFlockAddMes();
     void IMMessageSava(SysImMessage sysImMessage);

     void IMMessageDel(String UserId);

     Map<String,Object> upload(HttpServletRequest request,MultipartFile file) throws Exception;

     Map<String, Object> download(String uuid, HttpServletRequest request, HttpServletResponse response);

     List<Map<String,Object>> friendGroupAndUserList(String GroupId,String userId);

     void TimflockReadAdd(String MesId,String user_id,String Imgroup);

}
