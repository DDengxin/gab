package com.tengzhi.business.system.core.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.core.model.SysGenNote;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SysGenNoteDao extends BasedaoRepository<SysGenNote, SysGenNote.SysGenNoteRolePK>  {
    SysGenNote findByTableNameAndPrefixAndDateFormatAndDateAndLengthAndCorpId(String tableName,String Prefix,String dateFormat,String date,Integer length,String corpId);
}
