package com.tengzhi.business.tooling.toolingstore.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.tooling.toolingstore.dao.ToolingStoreDao;
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
public class ToolingStoreDaoImpl extends BasicsDaoImpl<EckOut,EckOut.ECkOut_PK> implements ToolingStoreDao {

    @Override
    public List<Map<String, Object>> queryAllOutboundStorage(Map<String,String> map) {
        Object srchCode = map.get("srchCode");
        Object srchName = map.get("srchName");
        Object srchSize = map.get("srchSize");
        Object lib = map.get("lib");
        String sqlWhere = "";
        if (srchCode != null && srchCode.toString().trim().length() > 0) {
            sqlWhere += " and  code like '%" + srchCode + "%' ";
        }
        if (srchName != null && srchName.toString().trim().length() > 0) {
            sqlWhere += " and cpcodename like '%" + srchName + "%' ";
        }
        if (srchSize != null && srchSize.toString().trim().length() > 0) {
            sqlWhere += " and cpcode_size like '%" + srchSize + "%' ";
        }
        String sql =  "select  code cpcode_id, cpcodename cpcode_name, cpcode_size, cpcode_size_en, cpcode_xp,cpcodeflname cpcode_fl,\n" +
                "\tcpcode_type,\n" +
                "\tf_getparamname('GETBYPARENTID',cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"') cpcode_bz,\n" +
                "\tcpcode_pb,\n" +
                "\tf_get_param_name('检验档案',cpcode_check,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_check,\n" +
                "\tcpcode01,\n" +
                "\tcpcode02,\n" +
                "\tcpcode03,\n" +
                "\tsl sl,\n" +
                "\tpack out_pack " +
                "from v_ck_mx where lib='"+lib+"' and cpcode_fl='GZMJ' "+sqlWhere;

        //转驼峰
        return super.queryToMapTransformers(sql);
    }


    @Override
    public List<Map<String, Object>> queryOutboundRecords(Map<String, String> map) {
        Object srchCode = map.get("srchCode");
        Object srchName = map.get("srchName");
        Object srchSize = map.get("srchSize");
        String sqlWhere = "";
        if (srchCode != null && srchCode.toString().trim().length() > 0) {
            sqlWhere += " and  cpcode_id like '%"+srchCode+"%' ";
        }
        if (srchName != null && srchName.toString().trim().length() > 0) {
            sqlWhere += " and f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+srchName+"%' ";
        }
        if (srchSize != null && srchSize.toString().trim().length() > 0) {
            sqlWhere += " and cpcode_size like '%"+srchSize+"%' ";
        }
        String sql = "select out_code,cpcode_id,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,"
        		+ "mj_sl,mj_cpcode,out_pack,mj_code out_remarks from e_ck_out a ,e_js_cpcode b,m_gz_mjrecord c where a.out_note=c.mj_note and a.out_code=c.mj_cpcode and a.out_code=b.cpcode_id "+sqlWhere  ;
        
        return super.queryToMapTransformers(sql);
    }


    @Override
    public List<Map<String, Object>> queryHomepageRecord(Map<String, String> map) {
        String where= SqlJoint.whereSuffixWhere(e->{
            e.contains(" out_note ",map.get("srchoutNote"));
            e.eq(" out_customer ",map.get("srchSupplier"));
            e.between("out_rq",map.get("srchRq1"),map.get("srchRq2"));
            e.eq("out_act",map.get("Act"));
        });
        String Sql="SELECT\n" +
                "\tout_rq,\n" +
                "\tout_note,\n" +
                "\tout_act,\n" +
                "\tout_lib,\n" +
                "\tf_get_param_name('库房档案',out_lib,'"+   SessionUser.getCurrentCorpId()   +"') out_lib_name,\n" +
                "\tout_customer,\n" +
                "\tf_getname('GETDWNAME',out_customer,'',data_corp) out_customer_name,\n" +
                "\tout_lyr,\n" +
                "\tf_getname('GETUSERNAME',out_lyr,'',data_corp) out_lyr_name,\n" +
                "\tsum(out_sl) out_sl,\n" +
                "\tmulti_mould_column(out_flag) out_flag,\n" +
                "\tdata_corp,\n" +
                "\tf_getname('GETUSERNAME',data_man,'',data_corp) data_man_name,\n" +
                "\tdata_date,\n" +
                "\tf_getname('GETUSERNAME',out_man,'',data_corp) out_man_name,\n" +
                "\tout_date\n" +
                "FROM\n"+
                "\te_ck_out \n"+where+
                "\tGROUP BY\n" +
                "\tdata_corp,out_rq,\n" +
                "\tout_note,\n" +
                "\tout_act,\n" +
                "\tout_customer,\n" +
                "\tout_lyr,\n" +
                "\tout_lib,\n" +
                "\tdata_corp,\n" +
                "\tdata_man,\n" +
                "\tdata_date,\n" +
                "\tout_man,\n" +
                "\tout_date";
        //转驼峰
        return super.queryToMapTransformers(Sql);
    }

    @Override
    public List<Map<String, Object>> checkRawMaterials(String Note,String ACT) {
        String where= SqlJoint.whereSuffixAnd(e->{
            e.eq("a.out_note",Note);
            e.eq("a.out_act",ACT);
        });
        String sql = "select out_code,mj_code out_remarks,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,cpcode_size,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl,out_type,mj_sl out_sl,out_pack,out_hs,"
        		+ "mj_sl,mj_code from e_ck_out a ,e_js_cpcode b,m_gz_mjrecord c where a.out_note=c.mj_note and a.out_code=c.mj_cpcode and a.out_code=b.cpcode_id "+where  ;

        //转驼峰
        return super.queryToMapTransformers(sql);
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

    @Transactional
    @Modifying
	@Override
	public int insertOutData(String note,String customer,String act,String lib,String userid,String corp_id,String outLyr) {
    	int cot=0;
    	/*cot=super.executeUpdateBysql("insert into e_ck_out(out_rq,out_note,out_customer,out_act,out_code,out_js,out_sl,out_pack,out_bpack,out_lib,out_type,out_flag,data_man,data_date) " + 
				"select mj_rq,mj_note,'"+customer+"','"+act+"',mj_cpcode,1,sum(mj_sl),ly_pack,ly_pack,'"+lib+"','WL','登记','"+userid+"',now() from m_gz_mjda where mj_note='"+note+"' group by mj_rq,mj_note,mj_cpcode,ly_pack" );*/
    	cot=super.executeUpdateBysql("insert into e_ck_out(out_rq,out_note,out_customer,out_act,out_code,out_js,out_sl,out_pack,out_bpack,out_lib,out_type,out_flag,data_man,data_date,data_corp,out_lyr) " + 
				"select mj_rq,mj_note,'"+customer+"','"+act+"',mj_cpcode,1,sum(mj_sl),ly_pack,ly_pack,'"+lib+"','WL','登记','"+userid+"',now(),'"+corp_id+"','"+outLyr+"' from m_gz_mjrecord where mj_note='"+note+"' group by mj_rq,mj_note,mj_cpcode,ly_pack" );
		return cot;
	}

}
