package com.tengzhi.business.xt.notice.deptmeeting.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.file.FileUtil;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.upload.dao.SysUploadDao;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.xt.notice.deptmeeting.dao.DeptMeetingDao;
import com.tengzhi.business.xt.notice.deptmeeting.model.DeptMeeting;
import com.tengzhi.business.xt.notice.deptmeeting.service.DeptMeetingService;
import com.tengzhi.business.xt.notice.model.EXtNotice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class DeptMeetingImpl implements DeptMeetingService {
    @Autowired
    DeptMeetingDao DeptMeetingDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysUploadDao sysUploadDao;

    @Autowired
    private Environment environment;
    @Autowired
    private SysParamDao sysParamDao;

    @Override
    public BasePage<Map<String, Object>> getNotice(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String where = SqlJoint.whereSuffixAnd((c) -> {
            c.ge("rq", map.get("srchRq1"));
            c.le ("rq", map.get("srchRq2"));
            c.contains("hydept", map.get("srch_dept"));
            c.eq("data_corp",map.get("srchCorp"));
            c.contains("hytitle", map.get("srch_title"));
        });

        String sql = "SELECT note,to_char(rq,'yyyy-mm-dd')rq,f_getname(data_corp,'yyyy-mm-dd')corpname,hyadd,hytitle,hydept,man,flag,data_corp,(select line_primary from com_file f where f.line_primary=e.file)file FROM e_xt_deptmeeting e "
                + " WHERE  1=1 " + where + " ORDER BY rq DESC ";
        return DeptMeetingDao.QueryMapPageList(baseDto, sql, true);
    }


    @Override
    public DeptMeeting save(DeptMeeting deptMeeting) throws Exception {
        //根据菜单类型不同修改不同的单号前缀
        /*String prefix = NotePrefix(eXtNotice);
        eXtNotice.setNoticeNo(sysGenNoteService.getyyMMNote4(EXtNotice.class, prefix));*/
        deptMeeting.setNote(sysGenNoteService.getNoteDate("",new Date(),"HY","yyMM",4));
        SessionUser securityUser = SessionUser.SessionUser();
        deptMeeting.setFlag("登记");
        deptMeeting.setMan(securityUser.getUserId());
        return DeptMeetingDao.save(deptMeeting);

    }

    public String NotePrefix(EXtNotice model) {
        String entity = sysParamDao.getKeyParamValue1(model.getParamMod(), model.getNoticeType());
        if (StringUtils.isEmpty(entity)) {
            new RuntimeException("参数值不存在");
        }
        return entity;

    }


    @Override
    public void update(DeptMeeting deptMeeting) {
        SessionUser securityUser = SessionUser.SessionUser();
        deptMeeting.setFlag("登记");
        deptMeeting.setMan(securityUser.getUserId());
        DeptMeetingDao.dynamicSave(deptMeeting, DeptMeetingDao.findByNote(deptMeeting.getNote()));
    }

    @Override
    public Map<String, Object> findByNote(String note) {
        String sql = "SELECT * from e_xt_deptmeeting  WHERE  note = '" + note + "'";
        List<Map> DeptMeeting = DeptMeetingDao.QueryListMap(sql);
        Map<String, Object> map = DeptMeeting.get(0);
        map.put("ip", environment.getProperty("kkfileview.ip"));
        map.put("port", environment.getProperty("kkfileview.port"));

        if (ObjectUtil.isEmpty(map.get("fileName"))) {
            map.put("winstatus", true);
            map.put("show", "当前公告没有文件");
        } else {
            String fileName = map.get("fileName").toString();
            String extName = cn.hutool.core.io.FileUtil.extName(fileName).toLowerCase();

            if (FileUtil.KKFILE_VIEW_SUFFIXS.indexOf(extName) >= 0) {
                String uploadFileAbsolutePath = FileUtil.PathJoin(FileUtil.getUploadAbsPath(), map.get("filePath").toString());
                File file = new File(uploadFileAbsolutePath);
                if (!file.exists()) {
                    map.put("winstatus", true);
                    map.put("show", "当前本地文件已丢失");
                }
            } else {
                map.put("winstatus", true);
                map.put("show", "当前系统不支持(" + extName + ")格式展示");
            }
        }
        return map;

    }


    @Override
    public void deleteByNoticeNo(String note) {
        String fileId = DeptMeetingDao.getFileId(note);
        List<SysUpload> sysUploads = DeptMeetingDao.getUuid(fileId);
        FileUtil util = FileUtil.getInstance();
        for (SysUpload sysUpload : sysUploads) {
            if (!StrUtil.isNullOrUndefined(sysUpload.getUuid())) {
                sysUploadDao.deleteByUuid(sysUpload.getUuid());
                util.deletefile(sysUpload.getFile_path(), false);
            }
        }
        DeptMeetingDao.deleteID(note);
    }

}
