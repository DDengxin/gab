package com.tengzhi.business.tooling.grantandrecover.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.tooling.grantandrecover.dao.dispatcherDao;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecord;
import com.tengzhi.business.tooling.grantandrecover.pojo.MGzMjrecordPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */

@Repository
public class dispatcherDaoImpl extends BasicsDaoImpl<MGzMjrecord,MGzMjrecordPK> implements dispatcherDao{

    @Override
    public List<Map<String, Object>> queryAllOutboundStorage(Map<String,String> map) {
        String where = SqlJoint.whereSuffixWhere(e->{
            e.contains("mj_code",map.get("mjCpcode"));
            e.contains("cpcode_name",map.get("cpcodeName"));
            e.contains("mj_ksize",map.get("mjKsize"));
        });
        String Sql="select * from v_mj_kc_new"+where;
        //转驼峰
        return super.queryToMapTransformers(Sql);
    }


    @Override
    public List<Map<String, Object>> queryOutboundRecords(Map<String, String> map) {
        String Act= "MJ02".equals(map.get("mjAct"))?"MJ01":"MJ03";
        String where= SqlJoint.whereSuffixAnd(e->{
            e.contains("C.mj_code",map.get("mjCpcode"));
            e.contains("f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')",map.get("cpcodeName"));
            e.contains("C.mj_ksize",map.get("mjKsize"));
            e.eq(" C.mj_act  ",Act);
        });
        String Sql="SELECT DISTINCT \n" +
                "\tC.mj_code,\n" +
                "\ta.mj_cpcode, \n" +
                "\tf_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                "\tC.mj_ksize,\n" +
                "\tf_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,\n" +
                "\tC.mj_kid \n" +
                "FROM\n" +
                "\tm_gz_mjda A,\n" +
                "\te_js_cpcode b,\n" +
                "\tm_gz_mjrecord C \n" +
                "WHERE\n" +
                "\tA.mj_cpcode = b.cpcode_id \n" +
                "\tAND A.mj_cpcode = C.mj_cpcode and c.mj_flag in ('登记','确认')\n"+
                "\tand c.mj_kid not in(select mj_kid from m_gz_mjrecord where mj_act='"+map.get("mjAct")+"' and mj_flag='确认' ) \n"+where;        //转驼峰
        return super.queryToMapTransformers(Sql);
    }


    @Override
    public List<Map<String, Object>> queryHomepageRecord(Map<String, String> map) {
        String where= SqlJoint.whereSuffixWhere(e->{
            e.contains(" mj_note ",map.get("mjNote"));
            e.eq(" ly_dept ",map.get("lyDept"));
            e.between("mj_rq",map.get("srchRq1"),map.get("srchRq2"));
            e.eq("mj_act",map.get("mjAct"));
        });
        //multi_column是自己自定义的聚合函数 | 多行状态不是全部确认(我就不显示确认显示登记)
        String Sql="SELECT\n" +
                "\tmj_note,\n" +
                "\tto_char(mj_rq,'yyyy-MM-dd') mj_rq,\n" +
                "\tmj_act,\n" +
                "\tmulti_column(mj_flag) mj_flag,\n" +
                "\tcount(mj_sl) mj_sl,\n" +
                "\tly_dept,\n" +
                "\tly_dept_name,\n" +
                "\tly_man,\n" +
                "\tly_man_name,\n" +
                "\tdata_man,\n" +
                "\tdata_man_name \n" +
                "FROM\n"+
                "\tm_gz_mjrecord \n" +where+
                "\tGROUP BY\n" +
                "\tmj_note,\n" +
                "\tmj_rq,\n" +
                "\tmj_act,\n" +
                "\tly_dept,\n" +
                "\tly_dept_name,\n" +
                "\tly_man,\n" +
                "\tly_man_name,\n" +
                "\tdata_man,\n" +
                "\tdata_man_name order by mj_rq desc";
        //转驼峰
        return super.queryToMapTransformers(Sql);
    }

    @Override
    public List<Map<String, Object>> checkRawMaterials(String Note,String ACT) {
        String where= SqlJoint.whereSuffixAnd(e->{
            e.eq(" C.mj_note ",Note);
            e.eq(" C.mj_act  ",ACT);
        });
        String Sql="SELECT DISTINCT \n" +
                "\tC.mj_code,\n" +
                "\tf_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,\n" +
                "\tC.mj_ksize,\n" +
                "\t f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,\n" +
                "\tC.mj_sl, \n" +
                "\tC.mj_xksize, \n" +
                "\tC.mj_flag,C.mj_remarks,C.mj_kid,C.mj_cpcode\n" +
                "FROM\n" +
                "\tm_gz_mjda A,\n" +
                "\te_js_cpcode b,\n" +
                "\tm_gz_mjrecord C \n" +
                "WHERE\n" +
                "\tA.mj_cpcode = b.cpcode_id \n" +
                "\tAND A.mj_cpcode = C.mj_cpcode \n"+where;
        //转驼峰
        return super.queryToMapTransformers(Sql);
    }


    @Transactional
    @Modifying
    @Override
    public int moldModification(String val1,String val2,String val3,String note, String code, String act) {
        String sql="update m_gz_mjrecord set";
        int cot=0;
        if(StrUtil.isNotBlank(val1)){
            sql+=" mj_code='"+val1+"'";
        }
        if(StrUtil.isNotBlank(val2)){
            sql+=" ,mj_cpcode='"+val2+"'";
        }
        if(StrUtil.isNotBlank(val3)){
            sql+=" ,mj_xksize='"+val3+"'";
        }
        sql+=" where mj_note='"+note+"' and mj_act='"+act+"' and mj_code='"+code+"' ";
        if(StrUtil.isNotBlank(val1) || StrUtil.isNotBlank(val2) || StrUtil.isNotBlank(val3)){
            cot=super.executeUpdateBysql(sql);
        }
        return cot;
    }

}
