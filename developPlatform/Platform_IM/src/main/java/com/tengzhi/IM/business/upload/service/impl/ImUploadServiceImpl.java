package com.tengzhi.IM.business.upload.service.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.IM.business.upload.dao.ImUploadDao;
import com.tengzhi.IM.business.upload.model.imFile;
import com.tengzhi.IM.business.upload.service.ImUploadService;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ImUploadServiceImpl implements ImUploadService {

    @Autowired
    private ImUploadDao imUploadDao;


    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userid = securityUser.getUserId();
        if (file.isEmpty()) {
            rmap.put("status", false);
            rmap.put("message", "上传失败");
            return rmap;
        }
        Map<String, String> map = null;
        try {
            FileUtil util = FileUtil.getInstance();
            map = util.fileUpload(file);
        } catch (Exception e) {
            rmap.put("status", false);
            rmap.put("message", "上传失败");
            return rmap;
        }
        // TODO:处理表单数据
        imFile imFile = new imFile();
        // 保存到数据库
        String uuid = UUIDUtil.uuid();
        imFile.setUuid(uuid);
        imFile.setUpload_date(new Date());
        imFile.setUpload_user_id(userid);
        imFile.setFile_name(map.get("fileName"));
        imFile.setFile_path(map.get("path"));
        imFile.setFile_sufixx(map.get("suffix"));
        imUploadDao.save(imFile);
        rmap.put("states", true);
        rmap.put("message", "上传成功");
        rmap.put("fileId", imFile.getUuid());
        return rmap;
    }


    @Override
    public Map<String, Object> download(String uuid, HttpServletRequest request, HttpServletResponse response) {
        imFile imFile = imUploadDao.findById(uuid).orElse(null);
        Map<String, Object> rmap = new HashMap<String, Object>();
        FileUtil util = FileUtil.getInstance();
        if (null == imFile || StrUtil.isNullOrUndefined(imFile.getFile_path())) {
            rmap.put("status", false);
            rmap.put("message", "文件不存在");
            return rmap;
        } else {
            if (!util.getDownloadResponse(request, response, imFile.getFile_path(), imFile.getFile_name(), imFile.getFile_sufixx())) {
                rmap.put("status", false);
                rmap.put("message", "获取下载链接失败");
                return rmap;
            }
        }
        return rmap;
    }


}
