package com.tengzhi.business.mesGz.gzda1.dao;

import com.tengzhi.business.mesGz.gzda1.model.MGzMjda;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MGzMjdaDao extends BasedaoRepository<MGzMjda,String>{


    @Transactional
    @Modifying
    @Query("delete from MGzMjda where mjNote=?1 and mjCode=?2 ")
    int deleteByNoteACode(String Note,String Code);


}
