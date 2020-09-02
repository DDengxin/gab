package com.tengzhi.IM.business.message.service.impl;

import com.tengzhi.IM.business.message.dao.SysImMessageDao;
import com.tengzhi.IM.business.message.dao.SysImReadDao;
import com.tengzhi.IM.business.message.model.SysImMessage;
import com.tengzhi.IM.business.message.model.SysImRead;
import com.tengzhi.IM.business.message.service.SysImMessageService;
import com.tengzhi.IM.business.upload.service.ImUploadService;
import com.tengzhi.IM.layerIM.dao.IMDao;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysImMessageServiceImpl implements SysImMessageService {

    @Autowired
    private SysImMessageDao sysImMessageDao;

    @Autowired
    private ImUploadService imUploadService;

    @Autowired
    private SysImReadDao sysImReadDao;

    @Autowired
    private IMDao imDao;

    @Override
    public void IMMessageSava(SysImMessage sysImMessage) {
        sysImMessageDao.save(sysImMessage);
    }
    @Override
    public Result updateTimFlockAddMes() {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        imDao.exeSql("update tim_flock_add_mes set isread='1' where group_manager='"+securityUser.getUserId()+"' ");
        return Result.resultOk();
    }
    @Override
    public void IMMessageDel(String UserId) {
        sysImMessageDao.deleteById(UserId);
    }


    @Override
    public  Map<String,Object> upload(HttpServletRequest request,MultipartFile file) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,String> submap = new HashMap<String,String>();
        if (!file.isEmpty()) {
            Map<String, Object> uploadmap = imUploadService.uploadFile(file);
            map.put("code","0");
            map.put("msg", "上传过成功");
            submap.put("src", request.getContextPath()+"/Mes/download/"+uploadmap.get("fileId").toString());
        }else{
            submap.put("src", "");
            map.put("code","1");
            map.put("msg", "上传过程中出现错误，请重新上传");
        }
        map.put("data",submap);
        return  map;
    }




    @Override
    public Map<String, Object> download(String uuid, HttpServletRequest request, HttpServletResponse response) {
        return imUploadService.download(uuid, request, response);
    }

    @Override
    public List<Map<String, Object>> friendGroupAndUserList(String GroupId,String u) {
        return imDao.friendGroupAndUserList(GroupId,u);
    }

    @Override
    public void TimflockReadAdd(String MesId,String user_id,String Imgroup) {
        //百叶窗层叠消息给每个用户||每个用户对应一条消息保存
        SysImRead timFlockRead=new SysImRead();
        timFlockRead.setId(UUIDUtil.uuid());
        timFlockRead.setIsread("0");
        timFlockRead.setMessageId(MesId);
        timFlockRead.setReadUserId(user_id);
        timFlockRead.setGroupid(Imgroup);
        sysImReadDao.save(timFlockRead);
    }


}
