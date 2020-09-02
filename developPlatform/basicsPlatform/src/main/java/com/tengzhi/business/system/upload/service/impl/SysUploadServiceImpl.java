package com.tengzhi.business.system.upload.service.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.base.tools.file.FileUtil;
import com.tengzhi.base.tools.json.MapperFactory;
import com.tengzhi.business.resouces.vo.ResultVo;
import com.tengzhi.business.system.upload.dao.SysUploadDao;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.system.upload.service.SysUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysUploadServiceImpl implements SysUploadService {

    @Autowired
    private SysUploadDao sysUploadDao;

    @Override
    public Map<String, Object> getFileID() {
        Map<String, Object> rmap = new HashMap<String, Object>();
        rmap.put("uuid", UUIDUtil.uuid());
        rmap.put("status", true);
        return rmap;
    }

    @Override
    public Result listByViewid(String viewid) {
        Object[] val = {viewid};
        List<Map> list = sysUploadDao.QueryListMap("select * from com_file where line_primary= ?1 order by upload_date", val);
        if (null != list) {
            for (int i = 0, max = list.size(); i < max; i += 1) {
                String uploadFileAbsolutePath = FileUtil.getUploadFileAbsolutePath(String.valueOf(list.get(i).get("file_path")));
                list.get(i).put("_exists", cn.hutool.core.io.FileUtil.exist(uploadFileAbsolutePath));
            }
        }
        Result result = Result.resultOk(list);
        result.put("status", true);
        return result;
    }

    @Override
    public Map<String, Object> delete(String parms) throws IOException {
        Map<String, Object> rmap = new HashMap<String, Object>();
        Map<String, String> map = MapperFactory.getInstance().Str2Map(parms);
        String sql = " SELECT *  FROM COM_FILE WHERE 1=1 ";
        if (!StrUtil.isNullOrUndefined(map.get("uuid"))) {
            sql += "  AND UUID = '" + map.get("uuid") + "' ";
        } else if (!StrUtil.isNullOrUndefined(map.get("file_path"))) {
            sql += "  AND FILEPATH = '" + map.get("file_path") + "' ";
        } else if (!StrUtil.isNullOrUndefined(map.get("link_table")) && !StrUtil.isNullOrUndefined(map.get("link_primary"))) {
            sql += " AND LINK_TABLE = '" + map.get("link_table") + "' AND LINK_PRIMARY = '" + map.get("link_primary") + "' ";
        } else {
            rmap.put("status", false);
            rmap.put("message", "文件不存在");
            return rmap;
        }
        List<Map> com_files = sysUploadDao.QueryListMap(sql);
        if (null == com_files || com_files.size() <= 0) {
            rmap.put("status", false);
            rmap.put("message", "文件不存在");
        } else {
            FileUtil util = FileUtil.getInstance();
            int fail_count = 0;
            for (Map com_file : com_files) {
                if (sysUploadDao.deleteByUuid((String) com_file.get("uuid")) > 0 && !util.deletefile((String) com_file.get("file_path"), false)) {
                    fail_count += 1;
                }
            }
            rmap.put("status", true);
            rmap.put("count", fail_count);
            rmap.put("message", "删除成功");
        }
        return rmap;
    }

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, HttpServletRequest req) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        SecurityUser securityUser = SessionUser.SessionUser();
        String userid = "Anonymous";
        if (securityUser != null) {
            userid = securityUser.getUserId();
        }
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
        SysUpload com_file = new SysUpload();
        com_file.setLine_primary(req.getParameter("pid"));
        // 保存到数据库
        String uuid = UUIDUtil.uuid();
        com_file.setUuid(uuid);
        com_file.setParent_uuid(null);
        com_file.setUpload_date(new Date());
        com_file.setUpload_user_id(userid);
        com_file.setFile_name(map.get("fileName"));
        com_file.setFile_path(map.get("path"));
        com_file.setFile_sufixx(map.get("suffix"));
        com_file.setPower(null);
        com_file.setDenydelete("0");
        com_file.setDenydownload("0");
        com_file.setFile_backup(map.get("path"));
        sysUploadDao.add(com_file.getUuid(), com_file.getParent_uuid(), com_file.getUpload_date(), com_file.getUpload_user_id(), com_file.getFile_name(), com_file.getFile_path(),
                com_file.getFile_sufixx(), com_file.getline_primary(), com_file.getPower(), com_file.getDenydelete(), com_file.getDenydownload(), com_file.getFile_backup(),
                com_file.getConverter_html());
        rmap.put("states", true);
        rmap.put("message", "上传成功");
        rmap.put("data", com_file.getUuid());
        return rmap;
    }

    @Override
    public Map<String, Object> download(String uuid, HttpServletRequest request, HttpServletResponse response) {
        SysUpload com_file = sysUploadDao.getByUuid(uuid);
        Map<String, Object> rmap = new HashMap<String, Object>();
        FileUtil util = FileUtil.getInstance();
        if (null == com_file || StrUtil.isNullOrUndefined(com_file.getFile_path())) {
            rmap.put("status", false);
            rmap.put("message", "文件不存在");
            return rmap;
        } else if (!util.getDownloadResponse(request, response, com_file.getFile_path(), com_file.getFile_name(), com_file.getFile_sufixx())) {
            rmap.put("status", false);
            rmap.put("message", "获取下载链接失败");
            return rmap;
        }
        return rmap;
    }

    @Override
    public ResultVo preview(String uuid, String file_id, HttpServletRequest request) {
        if (!StrUtil.isNullOrUndefined(uuid)) {
            return previewByUuid(uuid, request);
        } else if (!StrUtil.isNullOrUndefined(file_id)) {
            String val = sysUploadDao.getUuid(file_id);
            return previewByUuid(val, request);
        } else {
            return ResultVo.error("找不到文件");
        }
    }

    @Override
    public void getImage(String linePrimary, String uuid, HttpServletResponse response) throws IOException {
        FileUtil.getInstance().writeImage(getImage(linePrimary, uuid), response);
    }

    @Override
    public InputStream getImage(String linePrimary, String uuid) throws IOException {
        if (StringUtils.isBlank(uuid)) {
            uuid = sysUploadDao.getUuid(linePrimary);
        }
        if (StringUtils.isNotBlank(uuid)) {
            SysUpload sysUpload = sysUploadDao.getByUuid(uuid);
            String uploadFileAbsolutePath = FileUtil.PathJoin(FileUtil.getUploadAbsPath(), sysUpload.getFile_path());
            return cn.hutool.core.io.FileUtil.getInputStream(uploadFileAbsolutePath);
        } else {
            return null;
        }
    }


    public ResultVo previewByUuid(String uuid, HttpServletRequest request) {
        SysUpload com_file = sysUploadDao.getByUuid(uuid);
        if (null == com_file) {
            return ResultVo.error("文件不存在");
        } else {
            ResultVo result = tryConvertOffice(com_file, request);
            if (result.getStatus()) {
                result.setData(com_file.getConverter_html());
                result.setDetail(com_file.getUuid());
            }
            return result;
        }
    }

    /**
     * 尝试转换Office文档
     *
     * @param com_file
     * @return
     */
    public ResultVo tryConvertOffice(SysUpload com_file, HttpServletRequest request) {
        FileUtil util = FileUtil.getInstance();
        Map<String, String> map = util.ConverterHtml(com_file.getFile_path(), com_file.getConverter_html());
        String flag = map.get("status");
        String path = map.get("path");
        String suffix = map.get("message");

        if ("Y".equals(flag)) {
            // 转换成功
            com_file.setConverter_html(path);
            sysUploadDao.update(com_file);
            return ResultVo.success(path, flag);
        } else if ("N".equals(flag)) {
            // 已转换
            return ResultVo.success(path, flag);
        } else if ("V".equals(flag)) {
            // 其他，可预览
            return ResultVo.success(path, flag);
        } else { // 不可预览
            ResultVo result = ResultVo.error(map.get("message"));
            result.setFlag(flag);
            return result;
        }
    }

    @Override
    public String readFile(String uuid, String file_id, HttpServletRequest request) {
        try {
            if (!StrUtil.isNullOrUndefined(uuid)) {
                return readFileByUuid(uuid, request).toString();
            } else if (!StrUtil.isNullOrUndefined(file_id)) {
                uuid = sysUploadDao.getUuid(file_id);
            }
            if (StringUtils.isNotEmpty(uuid)) {
                return readFileByUuid(uuid, request).toString();
            } else {
                return ResultVo.error("找不到文件").toString();
            }
        } catch (Exception e) {
            return ResultVo.error(e).toString();
        }
    }

    /**
     * 通过UUID获取文件内容
     *
     * @param uuid
     * @return file_type:txt,priture,video,unknown
     */
    public ResultVo readFileByUuid(String uuid, HttpServletRequest request) {
        SysUpload com_file = sysUploadDao.getByUuid(uuid);
        if (null == com_file) {
            return ResultVo.error("文件不存在");
        } else {
            String suffix = com_file.getFile_sufixx().toLowerCase();
            if (FileUtil.TEXT_SUFFIXS.contains(suffix)) {
                FileUtil util = FileUtil.getInstance();
                Map<String, String> map = util.readTxtFile(com_file.getFile_path());
                ResultVo result = ResultVo.success(map.get("data"), "txt");
                result.setFlag(suffix);
                return result;
            } else if (FileUtil.PICTURE_SUFFIXS.contains(suffix)) {
                // 图片
                return ResultVo.success(com_file.getFile_path(), "picture");
            } else if (FileUtil.VIDEO_SUFFIXS.contains(suffix)) {
                // 视频
                return ResultVo.success(com_file.getFile_path(), "video");
            } else if (FileUtil.OFFICE_SUFFIXS.contains(suffix)) {
                ResultVo result = tryConvertOffice(com_file, request);
                if (result.getStatus()) {
                    result.setMessage("office");
                    return result;
                } else {
                    return ResultVo.success(com_file.getFile_path(), "unknown");
                }
            } else {
                return ResultVo.success(com_file.getFile_path(), "unknown");
            }

        }
    }

    @Override
    public Map<String, Object> deleteBylinePrimary(String id) {
        sysUploadDao.findAll(Specifications.where((c) -> {
            c.contains("line_primary", id);
        })).forEach(e -> {
            FileUtil util = FileUtil.getInstance();
            util.deletefile(e.getFile_path(), false);
            sysUploadDao.deleteByUuid(e.getUuid());
        });
        Map<String, Object> rmap = new HashMap<String, Object>();
        rmap.put("status", true);
        rmap.put("message", "删除成功");
        return rmap;
    }

}
