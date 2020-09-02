package com.tengzhi.business.system.main.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.system.main.model.SysUserTodo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface SysUserTodoDao extends BasedaoRepository<SysUserTodo, String> {


    @Modifying
    @Query("update SysUserTodo set status=true,modifyTime=:date where id = :id ")
    void upadteId(@Param("date") Timestamp date, @Param("id") String id);


    @Modifying
    @Query("update SysUserTodo set past=2 where id = :id ")
    void up(@Param("id") String id);

    @Modifying
    @Query("update SysUserTodo set past=1 where id = :id ")
    void ap(@Param("id") String id);

}
