package com.tengzhi.business.xt.notice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SecurityUser;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.file.FileUtil;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.upload.dao.SysUploadDao;
import com.tengzhi.business.system.upload.model.SysUpload;
import com.tengzhi.business.xt.notice.dao.NoticeDao;
import com.tengzhi.business.xt.notice.model.EXtNotice;
import com.tengzhi.business.xt.notice.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticedao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    @Autowired
    private SysUploadDao sysUploadDao;

    @Autowired
    private Environment environment;
    @Autowired
    private SysParamDao sysParamDao;

    @Override
    public BasePage<Map<String, Object>> getNotice(BaseDto baseDto) throws IOException {
        Map<String, String> map = baseDto.getParamsMap();
        String where = "";
        if (ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where += " and  to_char( notice_rq  , 'yyyy-MM-dd') >='" + map.get("srchRq1") + "'";
        }
        if (ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where += " and  to_char( notice_rq  , 'yyyy-MM-dd') <='" + map.get("srchRq2") + "'";
        }
        if (StringUtils.isNotBlank(map.get("srch_type"))) {
            where += " AND notice_type = '" + map.get("srch_type") + "'";
        }
        if (StringUtils.isNotBlank(map.get("srch_dept"))) {
            where += " AND notice_dept = '" + map.get("srch_dept") + "'";
        }
        if (StringUtils.isNotBlank(map.get("srch_title"))) {
            where += " AND notice_theme like '%" + map.get("srch_title") + "%'";
        }
        if (StringUtils.isNotBlank(map.get("srch_mode"))) {
            where += " AND notice_type in (SELECT param_id from sys_param   where   parent_id = '" + map.get("srch_mode") + "'  and  param_value1 is not null and param_value1 <> '' )";
        }


        String sql = "SELECT notice_no  ,notice_theme,notice_type,notice_content,data_date, data_corp,notice_flag," +
                " (select line_primary from com_file f where f.line_primary=e.notice_file)notice_file," +
                " data_man,notice_dept, "
                + " to_char( notice_rq, 'yyyy-MM-dd' ) notice_rq,f_getname ( 'GETDEPTNAME', notice_dept, '', data_corp ) notice_dept_name, "
                + " f_getparamname('GETBYXTYPE',notice_type,'公文参数','协同',NULL,'"+   SessionUser.getCurrentCorpId()   +"') notice_type_name, "
                + " f_getname ( 'GETUSERNAME', data_man, '', data_corp ) data_man_name,f_getname ( 'GETCORPEXP', '', '', data_corp ) data_corp_name "
                + " FROM e_xt_notice e "
                + " WHERE delete_logo = FALSE and data_corp='"+ ((SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCorpId() +"' " + where ;
        return noticedao.QueryMapPageList(baseDto, sql, true);
    }


    @Override
    public List<EXtNotice> getNoticeEight() {
        return noticedao.findAll(Specifications.where((c) -> {
            c.eq("noticeFlag", "核准");
            c.eq("deleteLogo", false);
            c.addOrderBy("noticeRq", Sort.Direction.DESC);
            c.eq("dataCorp",SessionUser.getCurrentCorpId());
        }), PageRequest.of(0, 8)).getContent();
    }

    @Override
    public EXtNotice save(EXtNotice eXtNotice) throws Exception {
        //根据菜单类型不同修改不同的单号前缀
        String prefix = NotePrefix(eXtNotice);
        eXtNotice.setNoticeNo(sysGenNoteService.getyyMMNote4(EXtNotice.class, prefix));
        //eXtNotice.setNoticeNo(sysGenNoteService.getNoteDate("",new Date(),"GW","yyMM",4));
        if (judgeUnique(eXtNotice)) {
            eXtNotice.setDeleteLogo(false);
            SessionUser securityUser = SessionUser.SessionUser();
            eXtNotice.setNoticeRq(new Date());
            eXtNotice.setNoticeFlag("登记");
            eXtNotice.setDataMan(securityUser.getUserId());
            eXtNotice.setDataCorp(securityUser.getCorpId());
            eXtNotice.setDataDate(new Date());
            return noticedao.save(eXtNotice);
        } else {
            throw new Exception("编码已存在");
        }
    }

    public String NotePrefix(EXtNotice model) {
        String entity = sysParamDao.getKeyParamValue1(model.getParamMod(), model.getNoticeType());
        if (StringUtils.isEmpty(entity)) {
            new RuntimeException("参数值不存在");
        }
        return entity;

    }


    @Override
    public void update(EXtNotice eXtNotice) {
        SessionUser securityUser = SessionUser.SessionUser();
        eXtNotice.setDataMan(securityUser.getUserId());
        eXtNotice.setDataCorp(securityUser.getCorpId());
        eXtNotice.setDataDate(new Date());
        noticedao.dynamicSave(eXtNotice, noticedao.findByNoticeNo(eXtNotice.getNoticeNo()));
    }

    @Override
    public boolean judgeUnique(EXtNotice eXtNotice) {
        SessionUser sessionUser=SessionUser.SessionUser();
        return noticedao.findAll(Specifications.where((c) -> {
            c.eq("noticeNo", eXtNotice.getNoticeNo());
            c.eq("deleteLogo", false);
            c.eq("dataCorp",sessionUser.getCorpId());
        })).size() <= 0;
    }

    @Override
    public Map<String, Object> findByNoticeNo(String noticeNo) {
        String sql = "SELECT b.* , u.nick_name FROM ( SELECT e.notice_no \"noticeNo\", e.notice_rq \"noticeRq\", e.notice_theme \"noticeTheme\",f.uuid \"uuid\",f.file_path \"filePath\",f.file_name \"fileName\", e.notice_dept \"noticeDept\", e.notice_type \"noticeType\",param_turn(e.notice_type) type_name, e.notice_content \"noticeContent\", e.notice_file \"noticeFile\", e.notice_flag \"noticeFlag\", e.data_man \"dataMan\",delete_logo, e.data_date \"dataDate\", e.data_corp \"dataCorp\", d.dept_name FROM e_xt_notice e LEFT JOIN ( SELECT dept_id, dept_name FROM sys_dept ) d ON e.notice_dept = d.dept_id  left join com_file f  on e.notice_file=f.line_primary ) b LEFT JOIN ( SELECT user_id, nick_name FROM sys_user ) u ON u.user_id = b.\"dataMan\" WHERE b.delete_logo=false and b.\"noticeNo\" = '" + noticeNo + "'";
        List<Map> eXtNotice = noticedao.QueryListMap(sql);
        Map<String, Object> map = eXtNotice.get(0);
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
    public boolean sure(String noticeNo) {
        EXtNotice eXtNotice = noticedao.findByNoticeNo(noticeNo);
        if ("登记".equals(eXtNotice.getNoticeFlag())) {
            eXtNotice.setNoticeFlag("确认");
            SessionUser securityUser = SessionUser.SessionUser();
            eXtNotice.setDataMan(securityUser.getUserId());
            eXtNotice.setDataCorp(securityUser.getCorpId());
            eXtNotice.setDataDate(new Date());
            update(eXtNotice);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancel(String noticeNo) {
        EXtNotice eXtNotice = noticedao.findByNoticeNo(noticeNo);
        if ("确认".equals(eXtNotice.getNoticeFlag())) {
            eXtNotice.setNoticeFlag("登记");
            SessionUser securityUser = SessionUser.SessionUser();
            eXtNotice.setDataMan(securityUser.getUserId());
            eXtNotice.setDataCorp(securityUser.getCorpId());
            eXtNotice.setDataDate(new Date());
            update(eXtNotice);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteByNoticeNo(String noticeNo) {
        String fileId = noticedao.getFileId(noticeNo);
        List<SysUpload> sysUploads = noticedao.getUuid(fileId);
        FileUtil util = FileUtil.getInstance();
        for (SysUpload sysUpload : sysUploads) {
            if (!StrUtil.isNullOrUndefined(sysUpload.getUuid())) {
                sysUploadDao.deleteByUuid(sysUpload.getUuid());
                util.deletefile(sysUpload.getFile_path(), false);
            }
        }
        noticedao.deleteID(noticeNo);
    }




    @Override
    public Map<String, Object> getDes() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("jtzx",noticedao.QueryListMap("select 'jtzx' table_note,'集团资讯' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8" +
                "AND notice_type in (SELECT param_id from sys_param where parent_id = 'ZXLX' and param_value1 is not null and param_value1 <> '') "));
        map.put("jtgg",noticedao.QueryListMap("select 'jtgg' table_note,'集团公告' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8" +
                "AND notice_type in (SELECT param_id from sys_param where parent_id = 'GWLX' and param_value1 is not null and param_value1 <> '') "));
        map.put("jthy",noticedao.QueryListMap("select 'jthy' table_note,'集团会议' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8" +
                "AND notice_type in (SELECT param_id from sys_param where parent_id = 'HYLX' and param_value1 is not null and param_value1 <> '') "));
        map.put("jdgg",noticedao.QueryListMap("select 'jdgg' table_note,'基地公告' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8" +
                "AND notice_type in (SELECT param_id from sys_param where parent_id = 'QYGW' and param_value1 is not null and param_value1 <> '') "));
        map.put("jdhy",noticedao.QueryListMap("select 'jdhy' table_note,'基地会议' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8" +
                "AND notice_type in (SELECT param_id from sys_param where parent_id = 'QYHY' and param_value1 is not null and param_value1 <> '') "));

        map.put("xm",noticedao.QueryListMap("select 'xm' table_note,'项目' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));
        map.put("cx",noticedao.QueryListMap("select 'cs' table_note,'程序' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));
        map.put("qx",noticedao.QueryListMap("select 'qx' table_note,'权限' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));
        map.put("sj",noticedao.QueryListMap("select 'sj' table_note,'数据' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));

        map.put("wc",noticedao.QueryListMap("select 'wc' table_note,'外出计划' table_name,note,to_char(wc_rq,'yyyy-MM-dd')date1,'【'||f_getname('GETUSERNAME',wc_man,'',data_corp)||'】'||replace(wc_add,'其他',wc_addsm )||wc_type title from (select row_number()over() as rownum, * from e_xt_wcsq where wc_flag!='登记')t where rownum<=8"));
        map.put("ry",noticedao.QueryListMap("select 'ry' table_note,'人员外出' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));
        map.put("cl",noticedao.QueryListMap("select 'cl' table_note,'车辆外出' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));
        map.put("wp",noticedao.QueryListMap("select 'wp' table_note,'物品外出' table_name,notice_no note,to_char(notice_rq,'yyyy-MM-dd')date1,notice_theme title, notice_file from (select row_number()over() as rownum, * from e_xt_notice order by notice_rq desc)t where rownum<=8"));

        return map;
    }

}
