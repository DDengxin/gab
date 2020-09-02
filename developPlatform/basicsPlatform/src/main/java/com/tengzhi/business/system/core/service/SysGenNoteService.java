package com.tengzhi.business.system.core.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.system.core.model.SysGenNote;

import javax.validation.constraints.NotNull;
import java.util.Date;

public interface SysGenNoteService extends BaseService<SysGenNote, SysGenNote.SysGenNoteRolePK> {


    /**
     * 获取所有数据
     *
     * @param dto
     * @return
     */
    BasePage<SysGenNote> getList(BaseDto dto);


    /**
     * 生成单号，单号规则:Prefix+Date(yyyyMMdd)+number(长度= 4)
     *
     * @param clazz  实体类(必须包含Table或者Entity注解)
     * @param prefix 前缀
     * @return
     * @throws Exception
     */
    String getyyyyMMddNote4(Class clazz, String prefix);

    /**
     * 生成单号，单号规则:Prefix+Date(yyMM)+number(长度= 4)
     *
     * @param clazz  实体类(必须包含Table或者Entity注解)
     * @param prefix 前缀
     * @return
     * @throws Exception
     */
    String getyyMMNote4(Class clazz, String prefix) ;

    /**
     * 生成单号，单号规则:Prefix+Date+number
     *
     * @param clazz       实体类(必须包含Table或者Entity注解)
     * @param prefix      前缀
     * @param date_format 时间格式
     * @param length      长度
     * @return
     * @throws Exception
     */
    String getNote(Class clazz, String prefix, String date_format, int length);

    /**
     * 生成单号，单号规则:Prefix+Date+number
     *
     * @param tableName  表名
     * @param prefix     前缀
     * @param dateFormat 时间格式化字符串
     * @param length     顺序码长度
     * @return
     */
    String getNote(@NotNull String tableName, @NotNull String prefix, @NotNull String dateFormat, int length);

    /**
     * 根据指定时间生成单号，单号规则:Prefix+Date(yyyyMMdd)+number(长度= 4)
     *
     * @param clazz  实体类(必须包含Table或者Entity注解)
     * @param date   指定时间
     * @param prefix 前缀
     * @return
     * @throws Exception
     */
    String getyyyyMMddNoteDate4(Class clazz, Date date, @NotNull String prefix);

    /**
     * 根据指定时间生成单号，单号规则:Prefix+Date+number
     *
     * @param clazz       实体类(必须包含Table或者Entity注解)
     * @param date        指定时间
     * @param prefix      前缀
     * @param date_format 时间格式
     * @param length      长度
     * @return
     * @throws Exception
     * @Deprecated 该方法可以靠getNote实现，无需重写方法，请使用getNote方法，该方法将会在不久后移除
     */
    @Deprecated
    String getNoteDate(Class clazz, Date date, String prefix, String date_format, int length);

    /**
     * 根据指定时间生成单号，单号规则:Prefix+Date+number
     *
     * @param tableName  表名
     * @param dateValue  指定时间
     * @param prefix     前缀
     * @param dateFormat 时间格式化字符串
     * @param length     顺序码长度
     * @return
     * @Deprecated 该方法可以靠getNote实现，无需重写方法，请使用getNote方法，该方法将会在不久后移除
     */
    @Deprecated
    String getNoteDate(String tableName, Date dateValue, String prefix, String dateFormat, int length);

    /**
     * 根据指定行业，口径生成单号，单号规则: Corpid+industry+act+number
     * @param clazz 实体类(必须包含Table或者Entity注解)
     * @param industry 行业
     * @param act 口径
     * @return
     */
    String getInOutNote(Class clazz,String industry, String act,String corpId);
}
