package com.tengzhi.business.system.core.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.impl.BaseServiceImpl;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.log.Log;
import com.tengzhi.business.system.core.dao.SysGenNoteDao;
import com.tengzhi.business.system.core.model.SysGenNote;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;


@Service
@Scope(value = "singleton")
@Transactional
public class SysGenNoteServiceImpl extends BaseServiceImpl<SysGenNote, SysGenNote.SysGenNoteRolePK> implements SysGenNoteService {
    @Autowired
    private SysGenNoteDao sysGenNoteDao;

    @Override
    public BasePage<SysGenNote> getList(BaseDto dto) {
        Map<String,String> map = dto.getParamsMap();
        return sysGenNoteDao.QueryPageList(dto, Specifications.where((c) -> {
            c.contains(map,new String[]{"tableName","prefix","dateFormat"});
            c.eq("corpId",SessionUser.SessionUser().getCorpId());
        }));
    }

    @Override
    public String getyyyyMMddNote4(Class clazz, @NotNull  String prefix) {
        return getNote(clazz, prefix, "yyyyMMdd", 4);
    }
    @Override
    public String getyyMMNote4(Class clazz, @NotNull  String prefix) {
        return getNote(clazz, prefix, "yyMM", 4);
    }

    @Override
    public String getNote(Class clazz, String prefix, String date_format, int length) {
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (null == table && null == entity) {
            throw new RuntimeException("生成单号失败,传入的实体类没有Table或者Entity注解");
        } else {
            String table_name = (null == table ? entity.name() : table.name()).toUpperCase();
            return getNote(table_name, prefix, date_format, length);
        }
    }

    @Override
    public String getNote(@NotNull String tableName, @NotNull String prefix, @NotNull String dateFormat, int length) {
        SessionUser sessionUser = SessionUser.SessionUser();
        String corpId = "";
        if (sessionUser == null || sessionUser.getCorpId() == null) {
            corpId = SessionUser.getDefaultCorp();
        } else {
            corpId = sessionUser.getCorpId();
        }
//        String corpId = (null == sessionUser ? SessionUser.getDefaultCorp() : sessionUser.getCorpId()).trim();
        String date = DateFormatUtils.format(new Date(), dateFormat);
        //prefix默认增加账套前缀
        String realPrefix = String.format("%s%s", corpId, prefix.trim());

        SysGenNote sysGenNote = sysGenNoteDao.findById(new SysGenNote.SysGenNoteRolePK(prefix, dateFormat, date, tableName, corpId)).orElse(null);
        if (null == sysGenNote) {
            sysGenNote = new SysGenNote();

            sysGenNote.setTableName(tableName);
            sysGenNote.setPrefix(prefix);
            sysGenNote.setLength(length);
            sysGenNote.setDate(date);
            sysGenNote.setIncrement(Long.valueOf(1));
            sysGenNote.setDateFormat(dateFormat);
            sysGenNote.setCorpId(corpId);
            sysGenNoteDao.save(sysGenNote);
        } else {
            if (String.valueOf(sysGenNote.getIncrement()).length() > length) {
                Log.info(String.format("单号生成规则长度溢出,{}", sysGenNote));
            } else {
                sysGenNote.setIncrement(sysGenNote.getIncrement() + 1);
                sysGenNoteDao.update(sysGenNote);
            }
        }
        //拼接单号
        return String.format("%s%s%s", realPrefix, date, String.format("%0" + length + "d", sysGenNote.getIncrement()));
    }

    @Override
    public String getyyyyMMddNoteDate4(Class clazz, Date date,@NotNull  String prefix) {
        return getNoteDate(clazz, date,prefix, "yyyyMMdd", 4);
    }












    @Override
    public String getNoteDate(Class clazz, Date date,String prefix, String date_format, int length) {
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (null == table && null == entity) {
            throw new RuntimeException("生成单号失败,传入的实体类没有Table或者Entity注解");
        } else {
            String table_name = (null == table ? entity.name() : table.name()).toUpperCase();
            return getNoteDate(table_name,date, prefix, date_format, length);
        }
    }

    @Override
    public String getNoteDate(String tableName,Date dateValue, String prefix, String dateFormat, int length) {
        SessionUser sessionUser = SessionUser.SessionUser();
        String date = DateFormatUtils.format(dateValue, dateFormat);

        SysGenNote sysGenNote = sysGenNoteDao.findById(new SysGenNote.SysGenNoteRolePK(prefix,dateFormat,date,tableName,sessionUser.getCorpId())).orElse(null);
        if (null == sysGenNote) {
            sysGenNote = new SysGenNote();

            sysGenNote.setTableName(tableName);
            sysGenNote.setPrefix(prefix);
            sysGenNote.setLength(length);
            sysGenNote.setDate(date);
            sysGenNote.setIncrement(Long.valueOf(1));
            sysGenNote.setDateFormat(dateFormat);
            sysGenNote.setCorpId(sessionUser.getCorpId());
            sysGenNoteDao.save(sysGenNote);
        } else {
            if (String.valueOf(sysGenNote.getIncrement()).length() > length) {
                Log.info(String.format("单号生成规则长度溢出,{}", sysGenNote));
            } else {
                sysGenNote.setIncrement(sysGenNote.getIncrement() + 1);
                sysGenNoteDao.update(sysGenNote);
            }
        }
        //拼接单号
        return String.format("%s%s%s", prefix, date, String.format("%0" + length + "d", sysGenNote.getIncrement()));
    }

    @Override
    public String getInOutNote(Class clazz, String industry, String act,String corpId) {
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (null == table && null == entity) {
            throw new RuntimeException("生成单号失败,传入的实体类没有Table或者Entity注解");
        } else {
            String actPrefix = sysGenNoteDao.getSingleResult("select f_get_in_out_act('GETACTPREFIX',?1,?2,?3) "
                    ,act,industry,corpId);
            String table_name = (null == table ? entity.name() : table.name()).toUpperCase();
            return getNote(table_name, actPrefix, "yyMMdd", 4);
        }
    }
}
